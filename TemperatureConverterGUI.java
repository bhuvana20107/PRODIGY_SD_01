import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverterGUI extends JFrame {
    private JTextField inputField;
    private JComboBox<String> unitComboBox;
    private JTextArea resultArea;

    public TemperatureConverterGUI() {
        setTitle("Temperature Converter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        inputField = new JTextField(10);
        unitComboBox = new JComboBox<>(new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        JButton convertButton = new JButton("Convert");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Add components to the frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Enter temperature value:"), gbc);

        gbc.gridx = 1;
        add(inputField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Select unit of measurement:"), gbc);

        gbc.gridx = 1;
        add(unitComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(convertButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(new JScrollPane(resultArea), gbc);

        // Add action listener to the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertTemperature();
            }
        });
    }

    private void convertTemperature() {
        try {
            double temperature = Double.parseDouble(inputField.getText());
            int selectedUnitIndex = unitComboBox.getSelectedIndex();

            switch (selectedUnitIndex) {
                case 0:
                    displayResults(TemperatureConverter.celsiusToFahrenheit(temperature),
                            TemperatureConverter.celsiusToKelvin(temperature));
                    break;
                case 1:
                    displayResults(TemperatureConverter.fahrenheitToCelsius(temperature),
                            TemperatureConverter.fahrenheitToKelvin(temperature));
                    break;
                case 2:
                    displayResults(TemperatureConverter.kelvinToFahrenheit(temperature),
                            TemperatureConverter.kelvinToCelsius(temperature));
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid unit selection");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid temperature input. Please enter a valid number.");
        }
    }

    private void displayResults(double result1, double result2) {
        resultArea.setText("Result in Fahrenheit: " + result1 + "\nResult in Celsius: " + result2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverterGUI().setVisible(true);
            }
        });
    }
}

class TemperatureConverter {
    static double celsiusToFahrenheit(double t) {
        return (t * 9/5) + 32;
    }

    static double celsiusToKelvin(double t) {
        return t + 273.15;
    }

    static double fahrenheitToCelsius(double t) {
        return (t - 32) * 5/9;
    }

    static double fahrenheitToKelvin(double t) {
        return ((t-32) * 5/9) + 273.15;
    }

    static double kelvinToFahrenheit(double t) {
        return ((t-273.15) * 9/5) + 32;
    }

    static double kelvinToCelsius(double t) {
        return t - 273.15;
    }
}
