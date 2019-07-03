package it.polimi.se2019.Controller.Adrenalina;

public class Timer implements Runnable{

    @Override
    public void run() {

        try{
            Thread.sleep(5000);
            //Server.updateAll("Sono il timer!");

        }
        catch (InterruptedException e){

            e.printStackTrace();

        }

    }
}
