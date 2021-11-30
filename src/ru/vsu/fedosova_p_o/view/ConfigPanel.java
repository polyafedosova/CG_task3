package ru.vsu.fedosova_p_o.view;

import ru.vsu.fedosova_p_o.Parameters;
import ru.vsu.fedosova_p_o.fuction.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class ConfigPanel extends JPanel
{
    private ConfigWindow parentFrame;

    private JButton applyButton = new JButton("Apply");
    private JButton clearButton = new JButton("Clear");
    private JComboBox<String> functionSelection;

    private FunctionDescription currentFunction;
    private final String[] FUNCTION_TYPES = {new Function1().getFunctionName(), new Function2().getFunctionName(), new Function3().getFunctionName(),
                                            new Function4().getFunctionName(), new Function5().getFunctionName(), new Function6().getFunctionName()};
    private List<JTextField> parametersFields;


    public ConfigPanel(ConfigWindow parentFrame)
    {
        this.parentFrame = parentFrame;
        currentFunction = parentFrame.getParameterizableProvider().getFunction();

        functionSelection = new JComboBox<>(FUNCTION_TYPES);

        int currentFunctionNumber = 0;
        for(int i = 0; i < FUNCTION_TYPES.length; i++)
        {
            if(FUNCTION_TYPES[i].equals(currentFunction.getFunctionName()))
                currentFunctionNumber = i;
        }
        
        functionSelection.setSelectedIndex(currentFunctionNumber);

        parametersFields = fillParametersFieldArr();

        addButtonsListeners();
        addComboBoxListener();
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        Graphics2D gr = (Graphics2D) g;
        super.paintComponent(g);

        drawBackground(gr);
        paintComponents(gr);
    }


    private void paintComponents(Graphics2D gr) {
        paintButtons();

        functionSelection.setBounds((int) (0.2 * this.getWidth()), (int) (0.01 * this.getHeight()), (int) (0.6 * this.getWidth()), (int) (0.2 * this.getHeight()));
        functionSelection.setAlignmentX(LEFT_ALIGNMENT);
        functionSelection.setLightWeightPopupEnabled(false);
        add(functionSelection);

        paintParametersFields(gr);
        fillParametersFields();
    }

    private List<JTextField> fillParametersFieldArr()
    {
        List<JTextField> parametersFieldsArr = new LinkedList<>();
        for (int i = 0; i < currentFunction.getParameters().getCoefficientsList().size(); i++)
        {
            parametersFieldsArr.add(new JTextField());
        }
        return parametersFieldsArr;
    }

    private void paintParametersFields(Graphics2D g)
    {
        List<String> parameters = currentFunction.getRequiredParameters();

        double heightCoefficient = 0.8 / currentFunction.getParameters().getCoefficientsList().size() / 3;

        double coefficientY = 0.2 + heightCoefficient;

        for (int i = 0; i < parametersFields.size(); i++)
        {
            parametersFields.get(i).setBounds((int) (0.35 * this.getWidth()), (int) (coefficientY * this.getHeight()), (int) (0.5 * this.getWidth()), (int) (heightCoefficient * this.getHeight()));
            double finalCoeff = coefficientY;

            int finalI = i;
            drawWithFont(g, new Font("Serif", Font.BOLD, (int) (heightCoefficient / 2.5 * this.getWidth())), () ->
            {
                g.drawString("Parameter " + parameters.get(finalI), (int) (0.15 * this.getWidth()), (int) ((finalCoeff + heightCoefficient / 1.5) * this.getHeight()));
            });

            add(parametersFields.get(i));
            coefficientY += 3 * heightCoefficient;
        }
    }

    private void repaintPanel()
    {
        parametersFields = fillParametersFieldArr();
        removeAll();
        repaint();
    }

    private void addComboBoxListener()
    {
        functionSelection.addActionListener(e ->
        {
            if(Objects.equals(functionSelection.getSelectedItem(), FUNCTION_TYPES[0]))
            {
                currentFunction = new Function1();
                repaintPanel();
            }
           if(Objects.equals(functionSelection.getSelectedItem(), FUNCTION_TYPES[1]))
           {
                currentFunction = new Function2();
               repaintPanel();
           }
            if(Objects.equals(functionSelection.getSelectedItem(), FUNCTION_TYPES[2]))
            {
                currentFunction = new Function3();
                repaintPanel();
            }
            if(Objects.equals(functionSelection.getSelectedItem(), FUNCTION_TYPES[3]))
            {
                currentFunction = new Function4();
                repaintPanel();
            }
            if(Objects.equals(functionSelection.getSelectedItem(), FUNCTION_TYPES[4]))
            {
                currentFunction = new Function5();
                repaintPanel();
            }
            if(Objects.equals(functionSelection.getSelectedItem(), FUNCTION_TYPES[5]))
            {
                currentFunction = new Function6();
                repaintPanel();
            }
        });
    }

    private void paintButtons()
    {
        clearButton.setBounds((int)(0.86 * this.getWidth()), (int)((0.2 + (0.8 / 6)) * this.getHeight()), (int)(0.13 * this.getWidth()), (int)(0.8 / 6 * this.getHeight()));
        add(clearButton);

        applyButton.setBounds((int)(0.86 * this.getWidth()), (int)((0.2 + (0.8 / 6 * 4)) * this.getHeight()), (int)(0.13 * this.getWidth()), (int)(0.8 / 6 * this.getHeight()));
        add(applyButton);
    }

    private void addButtonsListeners()
    {
        clearButton.addActionListener(e ->
        {
            for(JTextField jTextField : parametersFields)
            {
                jTextField.setText(null);
            }
        });

        applyButton.addActionListener(e ->
        {
            Map<String, Double> newParametersMap = new LinkedHashMap<>();
            List<String> requiredParameters = currentFunction.getRequiredParameters();
            for (int i = 0; i < parametersFields.size(); i++) {
                if (parametersFields.get(i).getText() == null)
                {
                    newParametersMap.put(requiredParameters.get(i), 0.0);
                }
                else
                {
                    double parameter = Double.parseDouble(parametersFields.get(i).getText());
                    newParametersMap.put(requiredParameters.get(i), parameter);
                }
            }

            Parameters newParameters = new Parameters(newParametersMap);
            currentFunction.setParameters(newParameters);

            parentFrame.setCurrentFunction(currentFunction);
            parentFrame.onClick();
            parentFrame.dispose();
        });
    }

    private void fillParametersFields()
    {
        List<String> requiredParameters = currentFunction.getRequiredParameters();
        for (int i = 0; i < parametersFields.size(); i++) {
            parametersFields.get(i).setText(String.valueOf(currentFunction.get(requiredParameters.get(i))));
        }
    }

    private void drawBackground(Graphics2D gr)
    {
        GradientPaint backgroundGradientColor = new GradientPaint
                (
                        (int) (0.5 * this.getWidth()), this.getHeight(), new Color(0xADDFEA),
                        this.getWidth(), (int) this.getHeight(), new Color(0xFFFEF5));

        drawWithGradient(gr, backgroundGradientColor, () -> {
            gr.fillRect(
                    0,
                    0,
                    this.getWidth(),
                    this.getWidth()
            );
        });
    }

    private void drawWithGradient(Graphics2D g, GradientPaint gradient, Runnable drawAction)
    {
        Color oldC = g.getColor();
        g.setPaint(gradient);
        drawAction.run();
        g.setColor(oldC);
    }

    private void drawWithFont(Graphics2D g, Font font, Runnable drawAction)
    {
        Font oldF = g.getFont();
        g.setFont(font);
        drawAction.run();
        g.setFont(oldF);
    }


}
