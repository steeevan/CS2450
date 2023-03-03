/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jfontviewerv4;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/**
 *
 * @author estev
 */
public class JFontViewerv4 {
    int size = 10;
    String font = "Arial";
    String style;
    public JFontViewerv4()
    {
        // Create a jew JFrame container. Use the default border layout
        JFrame jfrm = new JFrame("Font Viewer");
        
        // Give the frame an initial size
        jfrm.setSize(610,410);
        
        // Terminate the program when the user closes the application
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.getContentPane().setLayout(new FlowLayout());
        //-----------------------------------------------------------------------------
        // create the first JPanel
        JPanel jpnlSlider = new JPanel(new BorderLayout());
        
        jpnlSlider.setPreferredSize(new Dimension(550,70));
        
        // make the panel opaque
        jpnlSlider.setOpaque(true);
        
        jpnlSlider.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        JLabel fontSLider = new JLabel("Size:");
        
        jpnlSlider.add(fontSLider,BorderLayout.WEST);
        
        JSlider slide = new JSlider(8,20);
        // Add major tick marks
        slide.setLabelTable(slide.createStandardLabels(2));
        slide.setMajorTickSpacing(2);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);
        jpnlSlider.add(slide,BorderLayout.SOUTH);
        
        
        //-------------------------------------------------------------------------
        JPanel jpnlOption = new JPanel(new GridLayout(1,3));
        
        jpnlOption.setPreferredSize(new Dimension(550,200));
        jpnlOption.setOpaque(true);
        jpnlOption.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        //-------------------------------------------------------------------------
        JPanel fontList = new JPanel(new BorderLayout());
        
        fontList.setPreferredSize(new Dimension(175,100));
        fontList.setOpaque(true);
        fontList.setBorder(BorderFactory.createLineBorder(Color.RED));
        //-----------------------------------------------------------------------
        String[] fontL = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        JList jList = new JList(fontL);
        jList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JScrollPane jScrolll = new JScrollPane(jList);
        JLabel fontLabel = new JLabel("Fonts:");
        //fontLabel.setPreferredSize(new Dimension(160,20));
        fontList.add(fontLabel,BorderLayout.WEST);
        fontList.add(jScrolll,BorderLayout.SOUTH);
        
        JPanel stylebox = new JPanel(new GridLayout(4,1));
        stylebox.setPreferredSize(new Dimension(15,100));
        stylebox.setOpaque(true);
        stylebox.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        
        JRadioButton regular = new JRadioButton("Regular");
        JRadioButton italic = new JRadioButton("Italic");
        JRadioButton bold = new JRadioButton("Bold");
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(regular);
        bg.add(italic);
        bg.add(bold);
        JLabel styleLabel = new JLabel("Style:");
        stylebox.add(styleLabel);
        stylebox.add(regular);
        stylebox.add(italic);
        stylebox.add(bold);
        
        JPanel checkBox = new JPanel(new GridLayout(8,1));
        checkBox.setPreferredSize(new Dimension(15,100));
        checkBox.setOpaque(true);
        checkBox.setBorder(BorderFactory.createLineBorder(Color.PINK));
        JLabel effectsLabel = new JLabel("Effects: ");
        checkBox.add(effectsLabel);
        JCheckBox caps = new JCheckBox("All caps");
        checkBox.add(caps);
        jpnlOption.add(fontList,BorderLayout.WEST);
        jpnlOption.add(stylebox,BorderLayout.CENTER);
        jpnlOption.add(checkBox,BorderLayout.EAST);
        
        JPanel jpnlText = new JPanel(new BorderLayout());
        jpnlText.setPreferredSize(new Dimension(550,80));
        jpnlText.setOpaque(true);
        jpnlText.setBorder(BorderFactory.createLineBorder(Color.CYAN));
        JLabel preview = new JLabel("the quick brown fox jumps over the lazy dog 0123456789 ");
        preview.setHorizontalAlignment(JLabel.CENTER);
        jpnlText.add(preview,BorderLayout.CENTER);
        
        jfrm.getContentPane().add(jpnlSlider);
        jfrm.getContentPane().add(jpnlOption);
        jfrm.getContentPane().add(jpnlText);
        jfrm.setLocationRelativeTo(null);
        jfrm.setVisible(true);
        
        slide.addChangeListener(new ChangeListener(){
            public void stateChanged(ChangeEvent ce){
                // If the slider is in the process of being changed, simply return
                if (slide.getValueIsAdjusting())
                    return;
                System.out.println(slide.getValue());
                preview.setFont(new Font(font,Font.PLAIN,slide.getValue()));
                size = slide.getValue();
            }
        } );
        
        jList.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent le)
            {
                int idx = jList.getSelectedIndex();
                if(idx != -1)
                    System.out.println("Current Selection " + fontL[idx]);
                font = fontL[idx];
                preview.setFont(new Font(font,Font.PLAIN,slide.getValue()));
            }
        });
        regular.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae){
        System.out.println(ae.getActionCommand());
        style = ae.getActionCommand();
        preview.setFont(new Font(font,Font.PLAIN,slide.getValue()));
    }
        });
        italic.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a) {
                System.out.println(a.getActionCommand());
                style = a.getActionCommand();
                preview.setFont(new Font(font,Font.ITALIC,slide.getValue()));
            }
        });
        bold.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent a) {
                System.out.println(a.getActionCommand());
                style = a.getActionCommand();
                preview.setFont(new Font(font,Font.BOLD,slide.getValue()));
            }
        });
        caps.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if(caps.isSelected()){
                    preview.setText(preview.getText().toUpperCase());
                }
                else {
                    preview.setText(preview.getText().toLowerCase());
                }
            }
        });
    }
    public void actionPerformed(ActionEvent ae){
        System.out.println(ae.getActionCommand());
    }
    public static void main(String[] args) {
        // TODO code application logic here
        // create the fram on the event dispatching thread
        SwingUtilities.invokeLater(()-> new JFontViewerv4());
    }
    
}
