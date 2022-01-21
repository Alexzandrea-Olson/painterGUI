import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Paint;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController  {
	
	// enum representing pen sizes
	   private enum PenSize {
	      SMALL(2), 
	      MEDIUM(4), 
	      LARGE(6);
	      
	      private final int radius;
	      
	      PenSize(int radius) {this.radius = radius;}
	      
	      public int getRadius() {return radius;}
	   };


	   //fxml variables
    @FXML
    private Slider alphaSlider;

    @FXML
    private TextField alphaTextField;

    @FXML
    private Slider blueSlider;

    @FXML
    private TextField blueTextField;

    @FXML
    private Button clearButton;

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private Slider greenSlider;

    @FXML
    private TextField greenTextField;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private Slider redSlider;

    @FXML
    private TextField redTextField;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private Button undoButton;
    
    private PenSize radius = PenSize.MEDIUM; // radius of circle
    // instance variables for managing color
    private int red = 0;
    private int green = 0;
    private int blue = 0;
    private double alpha = 1.0;

    @FXML
    void initialize() {
    	//setting radio buttons to different sizes
        smallRadioButton.setUserData(PenSize.SMALL);
        mediumRadioButton.setUserData(PenSize.MEDIUM);
        largeRadioButton.setUserData(PenSize.LARGE); 
        
        // binding TextField values to corresponding Slider values
        redTextField.textProperty().bind(              
           redSlider.valueProperty().asString("%.0f"));
        greenTextField.textProperty().bind(
           greenSlider.valueProperty().asString("%.0f"));
        blueTextField.textProperty().bind(
           blueSlider.valueProperty().asString("%.0f"));
        alphaTextField.textProperty().bind(
           alphaSlider.valueProperty().asString("%.2f"));
        
        // listener that set myColor fill based on Slider changes
        redSlider.valueProperty().addListener(                              
           new ChangeListener<Number>() {                                   
              @Override                                                     
              public void changed(ObservableValue<? extends Number> ov,     
                 Number oldValue, Number newValue) {                        
                 red = newValue.intValue();   
               
              }
           }
        );         
     // listener that set myColor fill based on Slider changes
        greenSlider.valueProperty().addListener(
           new ChangeListener<Number>() {                                   
              @Override                                                     
              public void changed(ObservableValue<? extends Number> ov,     
                 Number oldValue, Number newValue) {                        
                 green = newValue.intValue();
              }
           }
        );   
     // listener that set myColor fill based on Slider changes
        blueSlider.valueProperty().addListener(
           new ChangeListener<Number>() {                                   
              @Override                                                     
              public void changed(ObservableValue<? extends Number> ov,     
                 Number oldValue, Number newValue) {                        
                 blue = newValue.intValue();
              }
           }
        );          
     // listener that set myColor fill based on Slider changes
        alphaSlider.valueProperty().addListener(
           new ChangeListener<Number>() {                                   
              @Override                                                     
              public void changed(ObservableValue<? extends Number> ov,     
                 Number oldValue, Number newValue) {                        
                 alpha = newValue.doubleValue();
              }
           }
        );  
       
    }

    @FXML
    void clearButtonPressed(ActionEvent e) {
        drawingAreaPane.getChildren().clear(); // clear the canvas
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent e) {
        Color myColor = Color.rgb(red, green, blue,alpha);//user color from sliders
        Paint brushColor = myColor; // drawing color
    	
        		//drawing circle from the users mouse input
    		  Circle newCircle = new Circle(e.getX(), e.getY(),radius.getRadius(),brushColor);
    	      drawingAreaPane.getChildren().add(newCircle); 
    	     
    }

    @FXML
    void sizeRadioButtonSelected(ActionEvent e) {
    	 // user data for each size RadioButton is the corresponding PenSize
        radius = 
           (PenSize) sizeToggleGroup.getSelectedToggle().getUserData();
    }

    @FXML
    void undoButtonPressed(ActionEvent event) {
    	 int count = drawingAreaPane.getChildren().size();

         // if there are any shapes remove the last one added
         if (count > 0) {
            drawingAreaPane.getChildren().remove(count - 1);
         }
    }

}
