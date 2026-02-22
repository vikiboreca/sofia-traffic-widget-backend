package com.example.sofia_traffic_widget.Service;
import com.google.transit.realtime.GtfsRealtime;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service
public class GTFSRTService {
    public void getData(){
        try {
            // Replace with the path to your file
            FileInputStream inputStream = new FileInputStream("C:\\Users\\vikib\\Downloads\\vehicle-positions");

            // Parse the feed
            displayData(inputStream, 2);

            // Iterate through entities


            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void displayData(FileInputStream inputStream,int index) throws Exception{
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(inputStream);
        switch(index){
            case 1: for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
                if (entity.hasTripUpdate()) {
                    GtfsRealtime.TripUpdate update = entity.getTripUpdate();
                    System.out.println("Trip ID: " + update.getTrip().getTripId());

                    for (GtfsRealtime.TripUpdate.StopTimeUpdate stopTime : update.getStopTimeUpdateList()) {
                        System.out.println("Stop ID: " + stopTime.getStopId());
                        if (stopTime.hasArrival()) {
                            System.out.println("Arrival delay (s): " + stopTime.getArrival().getDelay());
                        }
                        if (stopTime.hasDeparture()) {
                            System.out.println("Departure delay (s): " + stopTime.getDeparture().getDelay());
                        }
                    }
                    System.out.println("-----");
                }
            }break;
            case 2: for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
                if (entity.hasVehicle()) {
                    GtfsRealtime.VehiclePosition vehicle = entity.getVehicle();
                    System.out.println("Vehicle ID: " + vehicle.getVehicle().getId());
                    System.out.println("Trip ID: " + vehicle.getTrip().getTripId());
                    System.out.println("Latitude: " + vehicle.getPosition().getLatitude());
                    System.out.println("Longitude: " + vehicle.getPosition().getLongitude());
                    System.out.println("Speed: " + vehicle.getPosition().getSpeed());
                    System.out.println("-----");
                }
            }break;
            case 3: for (GtfsRealtime.FeedEntity entity : feed.getEntityList()) {
                if (entity.hasAlert()) {
                    GtfsRealtime.Alert alert = entity.getAlert();
                    System.out.println("Alert: " + alert.getHeaderText().getTranslation(0).getText());
                    System.out.println("Description: " + alert.getDescriptionText().getTranslation(0).getText());
                    System.out.println("-----");
                }
            }break;
            default: break;
        }
    }
}
