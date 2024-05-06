package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.Patient.Patient;
import com.medteamb.medteamb.model.Patient.PatientRefert;
import com.medteamb.medteamb.model.Patient.Requests;
import com.medteamb.medteamb.model.Patient.SpecialAppointments;
import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.repository.*;
import com.medteamb.medteamb.repository.Patient.PatientRepository;
import com.medteamb.medteamb.repository.Patient.RefertRepository;
import com.medteamb.medteamb.repository.Patient.SpecialAppointmentsRepository;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.ConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.PatientExceptions.NotFound;
import com.medteamb.medteamb.service.ResponseHandler.PatientResponse.PatientResponse;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.DTOmapper;
import com.medteamb.medteamb.service.dto.appointment.AppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.*;
import com.medteamb.medteamb.service.dto.patient.RefertDTO.RefertResponseDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentRequestDTO;
import com.medteamb.medteamb.service.dto.patient.SpecialAppointments.SpecialAppointmentResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PatientService {

    PatientRepository patientRepo;
    DoctorRepository doctorRepo;
    SecretaryRepository secretaryRepo;
    AppointmentRepository appointmentsRepo;
    RefertRepository refertRepo;
    SpecialAppointmentsRepository specialAppointmentsRepo;
    DTOmapper mapper;

    /*
▪ poter ricevere una conferma dell'appuntamento via email
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
*/
    public PatientService(PatientRepository patientRepo, DoctorRepository doctorRepo,
                          SecretaryRepository secretaryRepo, RefertRepository refertRepo,
                          SpecialAppointmentsRepository specialAppointmentsRepo,
                          DTOmapper mapper){
        this.patientRepo = patientRepo;
        this.mapper = mapper;
        this.refertRepo = refertRepo;
        this.specialAppointmentsRepo = specialAppointmentsRepo;
        this.doctorRepo = doctorRepo;
        this.secretaryRepo = secretaryRepo;
    }

    //CREATE
    public PatientResponse newPatient(PatientRequestDTO newPatient){
        checkIfExists(newPatient);
        Patient patient = mapper.mapFromRequestToPatient(newPatient);
        patientRepo.save(patient);
        return new PatientResponse(mapper.mapFromPatientToResponse(patient));
    }



    //poter prenotare un appuntamento online con il mio dottore,
    // specificando data, ora e motivo della visita

     public AppointmentResponseDTO newAppointmentRequest(AppointmentRequestDTO request){
            Optional<Appointment> check = appointmentsRepo.findByAppointmentDateTime(request.getAppointmentDateTime());
            if (check.isPresent()){
                throw new ConflictException("appointment with this date and hour already exists for this patient");
            }
             Appointment goodRequest = mapper.mapFromAppointmentRequestToAppointment(request);
             appointmentsRepo.save(goodRequest);
             return mapper.mapFromAppointmentToResponseDTO(goodRequest);
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


    //READ
    // get patient by id
    public PatientResponse getPatient(Long id){
       PatientResponseDTO response =  mapper.mapFromPatientToResponse(patientRepo.findById(id).
               orElseThrow(() -> new NotFound("patient not found")));
       return new PatientResponse(response);
    }
    // get all patients
    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients = patientRepo.findAll();
        return patients.stream()
                .map(mapper::mapFromPatientToResponse)
                .collect(Collectors.toList());
    }
    // get more patients from ids
    public Iterable<Patient> getPatientsByIds(Iterable<Long> ids){
        return patientRepo.findAllById(ids);
    }

    // verificare disponibilità mio dottore
    // per id
    public ResponseForLists<AppointmentResponseDTO> getDocAvailabilityById(Long docID, int page, int pageSize){

        Page<Appointment> list = appointmentsRepo.getALlAvaibleAppointmentsOfOneDoctor(docID, PageRequest.ofSize(pageSize).withPage(page));

        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // per nome e cognome
    public ResponseForLists<AppointmentResponseDTO> getDocAvailabilityByNameAndSurname(String docName, String docSurname, int page, int size){
        Page<Appointment> list = appointmentsRepo.getAllAvaibleAppointmentsOfOneDocNameAndSurname( docName, docSurname, PageRequest.ofSize(size).withPage(page));

        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // poter visualizzare le visite ancora da effettuare
    public ResponseForLists<AppointmentResponseDTO> getAppointmentsToDo(Integer patientID, int page, int size){

        Page<Appointment> list = appointmentsRepo.getAllPatientAppointments( patientID, PageRequest.ofSize(size).withPage(page));

        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // poter visualizzare lo storico delle mie visite e i relativi referti
    public ResponseForLists<AppointmentResponseDTO> getAppointmentHistoryByPatientId(Integer patientID, int page, int size){

        Page<Appointment> list = appointmentsRepo.getHistoryOfPatientAppointmentsById( patientID, PageRequest.ofSize(size).withPage(page));

        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapIterableOfSlotDTO(list));
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

    // poter annullare un appuntamento esistente

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





