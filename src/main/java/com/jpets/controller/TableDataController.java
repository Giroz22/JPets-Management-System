package com.jpets.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Getter;

public class TableDataController<T> {

    @Getter
    private TableView<T> tableData;

    public TableDataController(TableView<T> tableData, Map<String,String> colums, List<T> data){

        this.tableData = tableData;
        this.createColumns(colums);
        this.setData(data);        
    }

    public void setData(List<T> data){        
        for(T entity : data){
            tableData.getItems().add(entity);
        }
    }

    private void createColumns(Map<String,String> colums){
        Iterator<String> it = colums.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();

            TableColumn<T, String> newColumn = new TableColumn<>(colums.get(key));

            newColumn.setCellValueFactory(new PropertyValueFactory<>(key));

            this.tableData.getColumns().add(newColumn);
        } 
    }
}
