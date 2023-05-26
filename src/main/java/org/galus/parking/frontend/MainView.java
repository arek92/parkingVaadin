package org.galus.parking.frontend;


import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import lombok.Getter;
import org.galus.parking.entity.Samochod;
import org.galus.parking.parkingService.Service;
import org.galus.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.w3c.dom.html.HTMLInputElement;

import java.net.HttpCookie;
import java.time.Duration;
import java.time.LocalDateTime;

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


    @Autowired
    public MainView(Service service) {


        buttonParkCar.addClickListener(buttonClickEvent -> {

            String registration = entryTextField.getValue();
            service.handleParkCar(registration, entryTime);
            entryTextField.clear();

        });

        buttonLeftParking.addClickListener(buttonClickEvent -> {
            String registrationNumber = leftTextField.getValue();
            service.handleLeftParking(registrationNumber);
            leftTextField.clear();


        });


        add(label, entryLabel, entryTextField, buttonParkCar, leftLabel, leftTextField, buttonLeftParking);
    }

}
