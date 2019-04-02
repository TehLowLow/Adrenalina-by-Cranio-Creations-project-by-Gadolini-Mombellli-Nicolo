package it.polimi.se2019.Model;

public class Connection {


    private String type;
    private Cell connectedCell;



    private  String getType(){return type;}

    private void setType(String tp){this.type = tp;}

    private Cell getConnectedCell(){return connectedCell;}

    private void setConnectedCell(Cell connect){this.connectedCell = connect;}

}
