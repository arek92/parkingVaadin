package org.galus.parking.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import org.galus.parking.parkingService.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

@Route("MainView")
@Getter
public class MainView extends VerticalLayout {

    private static LocalDateTime entryTime = LocalDateTime.now();
    private static final Label label = new Label("park car");
    private static final Label entryLabel = new Label("Entry");
    private static final TextField entryTextField = new TextField("registration number");
    private static final Button buttonParkCar = new Button("start parking");
    private static final Label leftLabel = new Label("Left Parking");
    private static final TextField leftTextField = new TextField("registration number");
    private static final Button buttonLeftParking = new Button("end parking");
    private static final AtomicInteger counter = new AtomicInteger(1);
    private static final AtomicInteger carLimit = new AtomicInteger(10);
    private static final Label carCounter = new Label("Car on parking : " + " " + (counter.intValue()-1));
    private static final Label carLimitLeft = new Label("free places on parking : " + " " + carLimit);



    @Autowired
    public MainView(Service service) {


        buttonParkCar.addClickListener(buttonClickEvent -> {


            String registration = entryTextField.getValue();
            service.handleParkCar(registration, entryTime);
            entryTextField.clear();

            int currentCounter = counter.getAndIncrement();
            int leftCars = carLimit.intValue() - currentCounter;
            carCounter.setText("liczba miejsc zajetych: " + currentCounter);
            carLimitLeft.setText("liczba miejsc wolnych: " + leftCars);
        });

        buttonLeftParking.addClickListener(buttonClickEvent -> {

            String registrationNumber = leftTextField.getValue();
            service.handleLeftParking(registrationNumber);
            leftTextField.clear();

            int currentCounter = counter.decrementAndGet()-1;
            int leftCars = carLimit.intValue() - currentCounter;
            carCounter.setText("liczba miejsc zajętych: " + currentCounter);
            carLimitLeft.setText("liczba miejsc wolnych: " + leftCars);
        });


        add(label, entryLabel, entryTextField, buttonParkCar, leftLabel, leftTextField, buttonLeftParking,carLimitLeft,carCounter);
    }

}
