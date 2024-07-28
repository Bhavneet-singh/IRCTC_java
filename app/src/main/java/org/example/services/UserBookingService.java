package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entites.Train;
import org.example.entites.User;
import org.example.utils.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookingService {

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<User> userList;

    private User user;

    private final String USER_FILE_PATH = "app/src/main/java/org/example/localDb/users.json";

    public UserBookingService(User user1) throws IOException {
        this.user = user1;
        loadUserListFromFile();
    }

    public UserBookingService() throws IOException {
        loadUserListFromFile();
    }

    private void loadUserListFromFile() throws IOException {
        userList = objectMapper.readValue(new File(USER_FILE_PATH), new TypeReference<List<User>>() {});
    }

    public Boolean loginUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {
            return user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword());
        }).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1){
        try{

            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_FILE_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    private boolean logInUser(){
        Optional<User> foundUser = userList.stream().filter(user1 -> {return user1.getName().equals(user.getName())
        && UserServiceUtil.checkPassword(user.getPassword() , user.getHashedPassword());}
        ).findFirst();
        return foundUser.isPresent();
    }

    public void fetchBooking(){
        Optional<User> userFectched = userList.stream().filter(user1 -> {return user1.getName().equals(user.getName())
                && UserServiceUtil.checkPassword(user.getPassword() , user.getHashedPassword());}
        ).findFirst();

        if(userFectched.isPresent()){
            userFectched.get().printTickets();
        }
    }

    public List<Train> getTrain(String source , String destination){
       try {
           TrainService trainService = new TrainService();
           return trainService.seachTrain(source, destination);
       }
       catch (IOException exception){
           return new ArrayList<>();
       }}

    public List<List<Integer>> fetchSeats(Train train){
           return train.getSeats();
        }
    public Boolean bookTrainSeat(Train train, int row, int seat) {
        try{
            TrainService trainService = new TrainService();
            List<List<Integer>> seats = train.getSeats();
            if (row >= 0 && row < seats.size() && seat >= 0 && seat < seats.get(row).size()) {
                if (seats.get(row).get(seat) == 0) {
                    seats.get(row).set(seat, 1);
                    train.setSeats(seats);
                    trainService.addTrain(train);
                    return true; // Booking successful
                } else {
                    return false; // Seat is already booked
                }
            } else {
                return false; // Invalid row or seat index
            }
        }catch (IOException ex){
            return Boolean.FALSE;
        }
    }




    }





