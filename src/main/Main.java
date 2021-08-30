package main;

import main.services.Instructions;
import main.model.Plateau;
import main.model.Rover;
import main.model.enums.Direction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("src/testFile.txt");

        Scanner scanner = new Scanner(file);

        out.println("Reading information...");
        String dimensions = scanner.nextLine().toUpperCase(Locale.ROOT);

        Plateau plateau = new Plateau(dimensions);

        int i = 0;
        while (scanner.hasNext()) {
            i++;

            String name = "Rover " + i;
            out.println("launching " + name + "...");
            String dropInfo = scanner.nextLine().toUpperCase(Locale.ROOT);

            try {
                Rover rover = dropRover(name, plateau, dropInfo);
                out.println("Report: " + rover.reportStatus());

                out.println("Processing instructions...");
                String instructions = scanner.nextLine().toUpperCase(Locale.ROOT);

                Direction[] directionsInstructions = new Instructions(instructions).getInstructions();
                rover.processInstructions(directionsInstructions);
                out.println("Report: " + rover.reportStatus());
            } catch (Exception ex) {
                scanner.close();
            }
        }
        out.println("That's more than a footstep for the mankind afterall. Thanks for exploring with us!");
    }

    private static Rover dropRover(String id, Plateau plateau, String roverCoordinates) {
        Rover rover = new Rover(id);
        rover.dropRover(plateau,  roverCoordinates);
        return rover;
    }
}
