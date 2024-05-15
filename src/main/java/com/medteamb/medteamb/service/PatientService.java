package com.medteamb.medteamb.service;

import com.medteamb.medteamb.model.agenda.Appointment;
import com.medteamb.medteamb.model.agenda.AppointmentStatus;
import com.medteamb.medteamb.model.patient.Patient;
import com.medteamb.medteamb.model.patient.PatientRefert;
import com.medteamb.medteamb.repository.*;
import com.medteamb.medteamb.repository.patient.PatientRepository;
import com.medteamb.medteamb.repository.patient.RefertRepository;
import com.medteamb.medteamb.repository.patient.SpecialAppointmentsRepository;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.ConflictException;
import com.medteamb.medteamb.service.ExceptionHandler.CustomException.NotFound;
import com.medteamb.medteamb.service.ResponseHandler.Response;
import com.medteamb.medteamb.service.ResponseHandler.ResponseForLists;
import com.medteamb.medteamb.service.dto.DTOmapper;
import com.medteamb.medteamb.service.dto.appointment.AppointmentResponseDTO;
import com.medteamb.medteamb.service.dto.patient.*;
import com.medteamb.medteamb.service.dto.patient.RefertDTO.RefertResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
       //poter richiedere farmaci ed impegnative di visite specialistiche
▪ poter ricevere una conferma dell'appuntamento via email
▪ poter ricevere prescrizioni di farmaci ed impegnative per visite specialistiche
*/
    public PatientService(PatientRepository patientRepo, DoctorRepository doctorRepo,
                          SecretaryRepository secretaryRepo, RefertRepository refertRepo,
                          SpecialAppointmentsRepository specialAppointmentsRepo, AppointmentRepository appointmentsRepo,
                          DTOmapper mapper){
    	this.appointmentsRepo = appointmentsRepo;
        this.patientRepo = patientRepo;
        this.mapper = mapper;
        this.refertRepo = refertRepo;
        this.specialAppointmentsRepo = specialAppointmentsRepo;
        this.doctorRepo = doctorRepo;
        this.secretaryRepo = secretaryRepo;
    }

    //CREATE
    public Response<PatientResponseDTO> newPatient(PatientRequestDTO newPatient){
        checkIfExists(newPatient);
        Patient patient = mapper.mapFromRequestToPatient(newPatient);
        patientRepo.save(patient);
        return new Response<>(mapper.mapFromPatientToResponse(patient));
    }

    //poter prenotare un appuntamento online con il mio dottore, specificando data, ora e motivo della visita

    public Response<AppointmentResponseDTO> newAppointmentRequest(PatientRequestAppointment patientRequestAppointment){
    	 Appointment appointment = appointmentsRepo.findById(patientRequestAppointment.getAppointmentID()).get();
    	 Patient patient = patientRepo.findById(patientRequestAppointment.getPatientID()).get();
            appointment.setPatient(patient);
            appointment.setStatus(AppointmentStatus.TO_DO);
            appointment.setMedicalService("Visita");
            appointment.setLocation("Develhope");
            appointment.setTaxCode(patient.getTaxCode());
            appointmentsRepo.save(appointment);
         return new Response<AppointmentResponseDTO>(mapper.mapFromAppointmentToResponseDTO(appointment));
    }

    //READ
    // get patient by id
    public Response<Patient> getPatient(Long id){
       PatientResponseDTO response =  mapper.mapFromPatientToResponse(patientRepo.findById(id).
               orElseThrow(() -> new NotFound("patient not found")));
       return new Response(response);
    }
    // get all patients
    public ResponseForLists<Patient> getAllPatients(int page, int size) {
        Page<Patient> patients = patientRepo.findAll(PageRequest.of(page,size));
        return new ResponseForLists<>(patients.toList());
    }

    // verificare disponibilità mio dottore

    // per id
    public ResponseForLists<AppointmentResponseDTO> getDocAvailabilityById(Long docID, int page, int pageSize){

        Page<Appointment> list = appointmentsRepo.getALlAvaibleAppointmentsOfOneDoctor(docID, PageRequest.ofSize(pageSize).withPage(page));
        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapFromIterableToAppointmentResponse(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // per nome e cognome
    public ResponseForLists<AppointmentResponseDTO> getDocAvailabilityByNameAndSurname(String docName, String docSurname, int page, int size){
        Page<Appointment> list = appointmentsRepo.getAllAvaibleAppointmentsOfOneDocNameAndSurname( docName, docSurname, PageRequest.ofSize(size).withPage(page));
        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapFromIterableToAppointmentResponse(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // poter visualizzare le visite ancora da effettuare
    public ResponseForLists<AppointmentResponseDTO> getAppointmentsToDoByPatientId(Integer patientID, int page, int size){

        Page<Appointment> list = appointmentsRepo.getAllPatientAppointments( patientID, PageRequest.ofSize(size).withPage(page));
        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapFromIterableToAppointmentResponse(list));
        response.setCurrentPage(list.getNumber());
        response.setNumOfPages(list.getTotalPages());
        response.setNumOfElements(list.getSize());
        response.setTotalElements(list.getTotalElements());
        return response;
    }
    // poter visualizzare lo storico delle mie visite e i relativi referti
    public ResponseForLists<AppointmentResponseDTO> getAppointmentHistoryByPatientId(Integer patientID, int page, int size){

        Page<Appointment> list = appointmentsRepo.getHistoryOfPatientAppointmentsById( patientID, PageRequest.ofSize(size).withPage(page));
        ResponseForLists<AppointmentResponseDTO> response = new ResponseForLists<>(mapper.mapFromIterableToAppointmentResponse(list));
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
    public Response<PatientResponseDTO> updatePatientById(Patient newPatient, Long id){
       Patient patient = patientRepo.findById(id).
               orElseThrow(() -> new NotFound("patient not found"));
       patient.updateThisPatient(newPatient);
       patientRepo.save(patient);
       return new Response<>(mapper.mapFromPatientToResponse(patient));
    }

    // poter annullare un appuntamento esistente

    //DELETE
    // delete 1 patient by id
    public Response<PatientResponseDTO> deletePatientById(Long id){
        Patient patient = patientRepo.findById(id).
                orElseThrow(() -> new NotFound("patient not found"));
        patientRepo.delete(patient);
        return new Response<>(mapper.mapFromPatientToResponse(patient));
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
        Optional<Patient> optionaleMail = patientRepo.findByPatientEmail(request.getPatientEmail());
        if (optionaleMail.isPresent()){
            throw new ConflictException("patient email " + request.getPatientEmail() + " already exists");
        }
    }


   // il paziente posta un referto // data di emissione non funziona
    public Response<RefertResponseDTO> postTestRefert(Long id, String request) {
        PatientRefert goodRefert = new PatientRefert();
        goodRefert.setPatient(patientRepo.findById(id).get());
        goodRefert.setDiagnosis(request);
        refertRepo.save(goodRefert);
        return new Response<>(mapper.mapFromRefertToResponseDTO(goodRefert));
    }
    // poter chiedere di spostare l’appuntamento esistente ove possibile
	public Response<AppointmentResponseDTO> moveAppointment(PatientUpdateAppointment patientUpdateAppointment) {
		Patient patient = patientRepo.findById(patientUpdateAppointment.getPatientID()).orElseThrow(
                ()-> new NotFound("patient not found ")
        );
		Appointment oldAppointment = appointmentsRepo.findById(patientUpdateAppointment.getOldAppointmentID()).orElseThrow(
                ()-> new NotFound("appointment not found ")
        );
		oldAppointment.setMedicalService(null);
		oldAppointment.setPatient(null);
		oldAppointment.setStatus(AppointmentStatus.EMPTY);
		oldAppointment.setTaxCode(null);

		PatientRequestAppointment patientRequestAppointment = new PatientRequestAppointment();
		patientRequestAppointment.setAppointmentID(patientUpdateAppointment.getNewAppointmentID());
		patientRequestAppointment.setPatientID(patient.getPatientID());

        return newAppointmentRequest(patientRequestAppointment);
	}

    public Response<AppointmentResponseDTO> cancelAppointment(PatientRequestAppointment requestAppointment) {
        Appointment appointment = appointmentsRepo.findById(requestAppointment.getAppointmentID()).get();
        Patient patient = patientRepo.findById(requestAppointment.getPatientID()).get();
        boolean check = appointment.getPatient().getPatientID() == patient.getPatientID();
        if (check)
        {
        appointment.setPatient(null);
        appointment.setStatus(AppointmentStatus.EMPTY);
        appointment.setMedicalService(null);
        appointment.setLocation(null);
        appointment.setTaxCode(null);
        appointmentsRepo.save(appointment);
        return new Response<>(mapper.mapFromAppointmentToResponseDTO(appointment));
        } else {
            throw new ConflictException("this appointment " + appointment + " dosen't belong to this patient " + patient.getPatientID());
        }
    }
}





