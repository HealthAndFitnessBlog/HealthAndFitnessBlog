package HealthAndFitnessBlog.entity;


public class Calculator {

    private int Age;
    private String Gender;
    private double Height;
    private double Weight;
    private String Activity;


    public Calculator(int age, String gender, double height, double weight, String activity ) {
        this.Age = age;
        this.Gender = gender;
        this.Height = height;
        this.Weight = weight;
        this.Activity = activity;

    }



    public int getAge() {return Age;}

    public void setAge(int age) {
        this.Age = age;
    }

    public String getGender() {return Gender;}

    public void setGender(String gender) {Gender = gender;}

    public double getHeight() {
        return Height;
    }

    public void setHeight(double height) {
        this.Height = height;
    }

    public double getWeight() {
        return Weight;
    }

    public void setWeight(double weight) {
        this.Weight = weight;
    }

    public String getActivity() {
        return Activity;
    }

    public void setActivity(String activity) {
        this.Activity = activity;
    }



    public int calculateCal(){


        Integer BMR = 0;
        Integer cal = 0;

        switch (this.Gender){

            case "M":
                BMR = (int)((10 * Weight) + (6.25 * Height) - (5 * Age) + 5);
                break;
            case "F":
                BMR = (int)((10 * Weight) + (6.25 * Height) - (5 * Age) - 161);
                break;
        }

        switch (this.Activity){

            case "1":
                cal = BMR;
                break;
            case "1.2":
                cal =(int)(BMR * 1.2);
                break;
            case "1.375":
                cal = (int)(BMR * 1.375);
                break;
            case "1.55":
                cal = (int)(BMR * 1.55);
                break;
            case "1.725":
                cal = (int)(BMR * 1.725);
                break;
            case "1.9":
                cal = (int)(BMR * 1.9);
                break;
            default:
                cal = 0;
                break;

        }

        return cal;
    }
}
