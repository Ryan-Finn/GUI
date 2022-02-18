package CookieClicker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class UpgradeModel {
    private Model model; //back reference since a purchase affect main model
    private int autoClickAmount = 0;
    private int level = 0;
    private String name = "";
    private ArrayList<Integer> cost = new ArrayList<>();
    private ArrayList<Integer> effect = new ArrayList<>();

    public UpgradeModel(String fileName, Model m) {
        model = m;

        //read in file
        try {
            InputStream inStream = getClass().getResourceAsStream(fileName);

            //Scanner in = new Scanner(Paths.get(file)); //old way
            Scanner in = new Scanner(inStream);
            name = in.nextLine();

            String line = in.nextLine();
            String[] tokens = line.split(" ");

            for (int i = 0; i < tokens.length; i += 2) {
                cost.add(Integer.parseInt(tokens[i]));
                effect.add(Integer.parseInt(tokens[i + 1]));
            }
            in.close();
        } catch (NullPointerException e) {
            System.out.println("file not found");
        }
    }

    public double getLevelCost() {
        return cost.get(level);
    }

    public boolean canPurchase(double money) {
        return cost.size() > 0 && money >= cost.get(level);
    }

    public int getAutoClick() {
        return autoClickAmount;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public void purchase(int money) {
        int upgradeCost = 0;
        if (canPurchase(money)) {
            autoClickAmount += effect.get(level);
            upgradeCost = cost.get(level);
            level++;
            model.adjustClickTotal(-upgradeCost);
        }
    }
}
