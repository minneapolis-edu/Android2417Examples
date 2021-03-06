package com.clara.hydrationroomembeddedrelation.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class HydrationViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    private RecordRepository recordRepository;
    private PersonWithRecordsRepository personWithRecordsRepository;

    public HydrationViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        recordRepository = new RecordRepository(application);
        personWithRecordsRepository = new PersonWithRecordsRepository(application);
    }

    public LiveData<List<Record>> getRecordsForPerson(int id) {
        return recordRepository.getAllRecordsForPerson(id);
    }

    public LiveData<List<Person>> getAllPeople(){
        return personRepository.getAllPeople();
    }

    public LiveData<Person> getPersonByName(String name) {
        return personRepository.getPersonByName(name);
    }

    public void insertPerson(Person... p) {
        personRepository.insert(p);
    }

    public void insertRecord(Record... r) {
        recordRepository.insert(r);
    }

//    public void insertPersonWithRecords(PersonWithRecords... pR) {
//        personWithRecordsRepository.insert(pR);
//    }

    public LiveData<PersonWithRecords> getPersonWithRecordsByName(String name) {
        return personWithRecordsRepository.getPersonWithRecordsByName(name);
    }


}
