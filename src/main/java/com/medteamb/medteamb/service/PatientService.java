package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Calendar.AppointmentSlot;
import com.medteamb.medteamb.model.Patient.Patient;
import com.medteamb.medteamb.model.Patient.PatientRefert;
import com.medteamb.medteamb.model.Patient.Requests;
import com.medteamb.medteamb.model.Patient.SpecialAppointments;
import com.medteamb.medteamb.repository.*;
import com.medteamb.medteamb.repository.Patient.PatientRepository;
import com.medteamb.medteamb.repository.Patient.PatientRequestsRepository;
import com.medteamb.medteamb.repository.Patient.RefertRepository;
import com.medteamb.medteamb.repository.Patient.SpecialAppointmentsRepository;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.ConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.NotFound;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.patient.*;
import com.medteamb.medteamb.service.dto.patient.AppointmentSlots.AvaibleAppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.PatientAppointmentDTO.*;
import com.medteamb.medteamb.service.dto.patient.RefertDTO.RefertResponseDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientService {

    PatientRepository patientRepo;
    AppointmentSlotRepo slotRepo;
    PatientRequestsRepository requestsForAppointmentsRepo;
    RefertRepository refertRepo;
    SpecialAppointmentsRepository specialAppointmentsRepo;
    DTOmapper mapper;

    PatientAppointmentMapper mapperForAppointment;
    /*
▪ poter ricevere una conferma dell'appuntamento via email
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
*/
    public PatientService(PatientRepository patientRepo, PatientRequestsRepository requestsForAppointmentsRepo,
                          AppointmentSlotRepo slotRepo, RefertRepository refertRepo,
                          SpecialAppointmentsRepository specialAppointmentsRepo,
                          DTOmapper mapper, PatientAppointmentMapper mapperForAppointment){
        this.patientRepo = patientRepo;
        this.requestsForAppointmentsRepo = requestsForAppointmentsRepo;
        this.mapper = mapper;
        this.mapperForAppointment = mapperForAppointment;
        this.refertRepo = refertRepo;
        this.slotRepo = slotRepo;
        this.specialAppointmentsRepo = specialAppointmentsRepo;
    }

    //CREATE
    public PatientResponse newPatient(PatientRequestDTO newPatient){
        checkIfExists(newPatient);
        return new PatientResponse(mapper.mapFromPatientToResponse
                (patientRepo.save(mapper.mapFromRequestToPatient(newPatient))));
    }

    //poter richiedere farmaci ed impegnative di visite specialistiche
    public SpecialAppointmentResponseDTO newSpecialAppointmentRequest(SpecialAppointmentRequestDTO specialAppointmentRequestDTO){
        Optional<Requests> check = specialAppointmentsRepo.findByAppointmentDateAndAppointmentHour(specialAppointmentRequestDTO.getAppointmentDate(), specialAppointmentRequestDTO.getAppointmentHour());
        if (check.isPresent()){
            throw new ConflictException("appointment with this date and hour already exists for this patient");
        }
        SpecialAppointments response =  new SpecialAppointments();
            response.setDetails(specialAppointmentRequestDTO.getDetails());
            response.setAppointmentHour(specialAppointmentRequestDTO.getAppointmentHour());
            response.setPatient(patientRepo.findById(specialAppointmentRequestDTO.getPatientID()).orElseThrow(
                    ()-> new NotFound("Patient not found")
            ));
            response.setAppointmentDate(specialAppointmentRequestDTO.getAppointmentDate());
            specialAppointmentsRepo.save(response);
            new SpecialAppointmentResponseDTO();
            return mapper.mapFromSpecialRequest(response);

    }


    //poter prenotare un appuntamento online con il mio dottore,
    // specificando data, ora e motivo della visita

     public AppointmentResponseDTO newAppointmentRequest(RequestForNewAppointmentDTO request){
            Optional<Requests> check = requestsForAppointmentsRepo.findByDayAndHour(request.getDay(), request.getHour());
            if (check.isPresent()){
                throw new ConflictException("appointment with this date and hour already exists for this patient");
            }
             Requests goodRequest = new Requests();
             goodRequest.setPatient(patientRepo.findById(request.getPatient_id()).orElseThrow(
                     ()-> new NotFound("patient with id: " + request.getPatient_id() + " not found")));
             goodRequest.setDay(request.getDay()); goodRequest.setDescription(request.getDescription());
             goodRequest.setHour(request.getHour());
             requestsForAppointmentsRepo.save(goodRequest);
             return mapper.mapFromRequestObjectToResponseDTO(goodRequest);
     }


    //READ
    // get patient by id
    public PatientResponse getPatient(Long id){
       PatientResponseDTO response =  mapper.mapFromPatientToResponse(patientRepo.findById(id).
               orElseThrow(() -> new NotFound("patient not found")));
       return new PatientResponse(response);
    }
    // get more patients from ids
    public Iterable<Patient> getPatientsByIds(Iterable<Long> ids){
        return patientRepo.findAllById(ids);
    }

    // verificare disponibilità mio dottore
    // per id
    public ResponseForLists<AvaibleAppointmentResponseDTO> getDocAvailabilityById(Integer docID, int page, int pageSize){

        Page<AppointmentSlot> list = slotRepo.getALlAvaibleAppointmentsOfOneDoctor(docID, PageRequest.ofSize(pageSize).withPage(page));

        ResponseForLists<AvaibleAppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // per nome e cognome
    public ResponseForLists<AvaibleAppointmentResponseDTO> getDocAvailabilityByNameAndSurname(String docName, String docSurname, int page, int size){
        Page<AppointmentSlot> list = slotRepo.getAllAvaibleAppointmentsOfOneDocNameAndSurname( docName, docSurname, PageRequest.ofSize(size).withPage(page));

        ResponseForLists<AvaibleAppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // poter visualizzare le visite ancora da effettuare
    public ResponseForLists<AvaibleAppointmentResponseDTO> getAppointmentsToDo(Integer patientID, int page, int size){

        Page<AppointmentSlot> list = slotRepo.getAllPatientAppointments( patientID, PageRequest.ofSize(size).withPage(page));

        ResponseForLists<AvaibleAppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // poter visualizzare lo storico delle mie visite e i relativi referti
    public ResponseForLists<AvaibleAppointmentResponseDTO> getAppointmentHistoryByPatientId(Integer patientID, int page, int size){

        Page<AppointmentSlot> list = slotRepo.getHistoryOfPatientAppointmentsById( patientID, PageRequest.ofSize(size).withPage(page));

        ResponseForLists<AvaibleAppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // poter caricare i miei referti
    public ResponseForLists<RefertResponseDTO> getRefertHistoryByPatientId(Long id, int page, int size){
        Page<PatientRefert> list = refertRepo.getHistoryOfRefertsByPatientID(id, PageRequest.of(page, size));
        ResponseForLists<RefertResponseDTO> response = new ResponseForLists<>( mapper.mapFromIterableToRefertResponseListDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }

    //UPDATE
    // update 1 patient by id with a newPatientIstance
    public PatientResponse updatePatientById(Patient newPatient, Long id){
       Patient patient = patientRepo.findById(id).
               orElseThrow(() -> new NotFound("patient not found"));
       patient.updateThisPatient(newPatient);
       patientRepo.save(patient);
       return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }
    // poter chiedere di spostare l’appuntamento esistente ove possibile
    public Requests updateAppointmentRequestToMove(RequestToMoveAppointmentDTO request){
        Requests oldAppointment = requestsForAppointmentsRepo.findByday(
                request.getDay()).orElseThrow( ()-> new NotFound("appointment not found"));

        oldAppointment.setDescription(request.getDescription());
        oldAppointment.setNewDate(request.getNewDay());
        oldAppointment.setNewHour(request.getNewHour());
        oldAppointment.appointmentToBeMoved();
        return requestsForAppointmentsRepo.save(oldAppointment);
    }

    // poter annullare un appuntamento esistente
    public Requests updateAppointmentToCancel(RequestToCancelAppointmentDTO request){
        Requests oldAppointment = requestsForAppointmentsRepo.findByday(
                request.getDate()).orElseThrow( ()-> new NotFound("appointment not found"));
        oldAppointment.appointmentCancelled();
        return requestsForAppointmentsRepo.save(oldAppointment);
    }

    //DELETE
    // delete 1 patient by id
    public PatientResponse deletePatientById(Long id){
        Patient patient = patientRepo.findById(id).
                orElseThrow(() -> new NotFound("patient not found"));
        patientRepo.delete(patient);
        return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }


    // UTILITIES

    public void checkIfExists(PatientRequestDTO request){
        Optional<Patient> optionalPhone = patientRepo.findBypatientPhoneNumber(request.getPatientPhoneNumber());
        if (optionalPhone.isPresent()){
            throw new ConflictException("patient phone number " + request.getPatientPhoneNumber()
            + " already exists");
        }
        Optional<Patient> optionalTaxCode = patientRepo.findBytaxCode(request.getTaxCode());
        if (optionalTaxCode.isPresent()){
            throw new ConflictException("patient taxCode " + request.getTaxCode()
            + " already exists");
        }
    }
   // POST REFERT TEST NOT FOR FINAL USE
    public RefertResponseDTO postTestRefert(Long id, String request) {
        PatientRefert goodRefert = new PatientRefert();
        goodRefert.setPatient(patientRepo.findById(id).get());
        goodRefert.setDiagnosis(request);
        refertRepo.save(goodRefert);
        return mapper.mapFromRefertToResponseDTO(goodRefert);
    }
}





