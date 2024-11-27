package com.jpets.home;

import com.jpets.utils.ViewUtil;

import javafx.scene.Scene;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class HomeView {
    private Scene MyScene; 

    public HomeView(){
        ViewUtil viewUtil = new ViewUtil();

        this.MyScene = viewUtil.getScene("/views/home/Home.fxml", new HomeController());
    }
}
