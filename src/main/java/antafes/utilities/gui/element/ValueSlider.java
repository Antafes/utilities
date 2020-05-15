/*
 * This file is part of Utilities.
 *
 * Utilities is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Utilities is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Utilities. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package Utilities
 * @author Marian Pollzien <map@wafriv.de>
 * @copyright (c) 2020, Marian Pollzien
 * @license https://www.gnu.org/licenses/lgpl.html LGPLv3
 */
package antafes.utilities.gui.element;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.SliderUI;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Dictionary;
import java.util.Hashtable;

public class ValueSlider extends JPanel
{
    private JSlider slider;
    private JLabel valueField;
    @Setter
    @Getter
    private String additionalText;

    public ValueSlider(LayoutManager layout, boolean isDoubleBuffered)
    {
        super(layout, isDoubleBuffered);
        initSlider(JSlider.HORIZONTAL, 0, 100, 50);
        initLabel();
    }

    public ValueSlider(LayoutManager layout)
    {
        super(layout);
        initSlider(JSlider.HORIZONTAL, 0, 100, 50);
        initLabel();
    }

    public ValueSlider(boolean isDoubleBuffered)
    {
        super(isDoubleBuffered);
        initSlider(JSlider.HORIZONTAL, 0, 100, 50);
        initLabel();
    }

    public ValueSlider()
    {
        this(JSlider.HORIZONTAL, 0, 100, 50);
    }

    public ValueSlider(int orientation)
    {
        this(orientation, 0, 100, 50);
    }

    public ValueSlider(int min, int max)
    {
        this(JSlider.HORIZONTAL, min, max, (min + max) / 2);
    }

    public ValueSlider(int min, int max, int value)
    {
        this(JSlider.HORIZONTAL, min, max, value);
    }

    public ValueSlider(int orientation, int min, int max, int value, String additionalText)
    {
        this.additionalText = additionalText;
        initSlider(orientation, min, max, value);
        initLabel();
        init();
    }

    public ValueSlider(int orientation, int min, int max, int value)
    {
        initSlider(orientation, min, max, value);
        initLabel();
        init();
    }

    public ValueSlider(BoundedRangeModel brm, String additionalText)
    {
        this.additionalText = additionalText;
        slider = new JSlider(brm);
        initLabel();
        init();
    }

    public ValueSlider(BoundedRangeModel brm)
    {
        slider = new JSlider(brm);
        initLabel();
        init();
    }

    private void initSlider(int orientation, int min, int max, int value)
    {
        slider = new JSlider(orientation, min, max, value);
    }

    private int calculateValueFieldWidth()
    {
        return String.valueOf(slider.getMaximum()).length();
    }

    private void initLabel()
    {
        valueField = new JLabel();
        valueField.setHorizontalAlignment(JLabel.LEFT);
        valueField.setFont(valueField.getFont().deriveFont(20f));
        valueField.setText(getFormattedValue());
        valueField.setSize(calculateValueFieldWidth(), getHeight());
        slider.addChangeListener(ce -> valueField.setText(getFormattedValue()));
    }

    /**
     * The value with optional additional text added.
     *
     * @return The value as string
     */
    private String getFormattedValue()
    {
        String value = "" + slider.getValue();

        if (additionalText != null && !additionalText.isEmpty()) {
            value += " " + additionalText;
        }

        return value;
    }

    private void init()
    {
        GridLayout layout = new GridLayout();
        layout.setRows(1);
        layout.setColumns(2);
        layout.setHgap(10);
        this.setLayout(layout);
        this.add(slider);
        this.add(valueField);
        this.addFocusListener();
    }

    /**
     * Add a focus listener to the panel which redirects the focus to the slider.
     */
    private void addFocusListener()
    {
        addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                slider.requestFocusInWindow();
            }

            @Override
            public void focusLost(FocusEvent e)
            {
            }
        });
        slider.addFocusListener(new FocusListener()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
            }

            @Override
            public void focusLost(FocusEvent e)
            {
                if (e.getCause().equals(FocusEvent.Cause.TRAVERSAL_FORWARD)) {
                    transferFocus();
                } else {
                    transferFocusBackward();
                }
            }
        });
    }

    /**
     * Gets the UI object which implements the L&amp;F for this component.
     *
     * @return the SliderUI object that implements the Slider L&amp;F
     */
    public SliderUI getSliderUI()
    {
        return slider.getUI();
    }

    /**
     * Sets the UI object which implements the L&amp;F for this component.
     *
     * @param ui the SliderUI L&amp;F object
     *
     * @beaninfo bound: true
     * hidden: true
     * attribute: visualUpdate true
     * description: The UI object that implements the slider's LookAndFeel.
     * @see UIDefaults#getUI
     */
    public void setUI(SliderUI ui)
    {
        slider.setUI(ui);
    }

    /**
     * Resets the UI property to a value from the current look and feel.
     *
     * @see JComponent#updateUI
     */
    public void updateSliderUI()
    {
        slider.updateUI();
    }

    /**
     * Returns the name of the L&amp;F class that renders this component.
     *
     * @return "SliderUI"
     * @see JComponent#getUIClassID
     * @see UIDefaults#getUI
     */
    public String getSliderUIClassID()
    {
        return slider.getUIClassID();
    }

    /**
     * Adds a ChangeListener to the slider.
     *
     * @param l the ChangeListener to add
     *
     * @see #removeChangeListener
     */
    public void addChangeListener(ChangeListener l)
    {
        slider.addChangeListener(l);
    }

    /**
     * Removes a ChangeListener from the slider.
     *
     * @param l the ChangeListener to remove
     *
     * @see #addChangeListener
     */
    public void removeChangeListener(ChangeListener l)
    {
        slider.removeChangeListener(l);
    }

    /**
     * Returns an array of all the <code>ChangeListener</code>s added
     * to this JSlider with addChangeListener().
     *
     * @return all of the <code>ChangeListener</code>s added or an empty
     * array if no listeners have been added
     * @since 1.4
     */
    public ChangeListener[] getChangeListeners()
    {
        return slider.getChangeListeners();
    }

    /**
     * Returns the {@code BoundedRangeModel} that handles the slider's three
     * fundamental properties: minimum, maximum, value.
     *
     * @return the data model for this component
     * @see #setModel
     * @see BoundedRangeModel
     */
    public BoundedRangeModel getModel()
    {
        return slider.getModel();
    }

    /**
     * Sets the {@code BoundedRangeModel} that handles the slider's three
     * fundamental properties: minimum, maximum, value.
     * <p>
     * Attempts to pass a {@code null} model to this method result in
     * undefined behavior, and, most likely, exceptions.
     *
     * @param newModel the new, {@code non-null} <code>BoundedRangeModel</code> to use
     *
     * @beaninfo bound: true
     * description: The sliders BoundedRangeModel.
     * @see #getModel
     * @see BoundedRangeModel
     */
    public void setModel(BoundedRangeModel newModel)
    {
        slider.setModel(newModel);
    }

    /**
     * Returns the slider's current value
     * from the {@code BoundedRangeModel}.
     *
     * @return the current value of the slider
     * @see #setValue
     * @see BoundedRangeModel#getValue
     */
    public int getValue()
    {
        return slider.getValue();
    }

    /**
     * Sets the slider's current value to {@code n}.  This method
     * forwards the new value to the model.
     * <p>
     * The data model (an instance of {@code BoundedRangeModel})
     * handles any mathematical
     * issues arising from assigning faulty values.  See the
     * {@code BoundedRangeModel} documentation for details.
     * <p>
     * If the new value is different from the previous value,
     * all change listeners are notified.
     *
     * @param n the new value
     *
     * @beaninfo preferred: true
     * description: The sliders current value.
     * @see #getValue
     * @see #addChangeListener
     * @see BoundedRangeModel#setValue
     */
    public void setValue(int n)
    {
        slider.setValue(n);
    }

    /**
     * Returns the minimum value supported by the slider
     * from the <code>BoundedRangeModel</code>.
     *
     * @return the value of the model's minimum property
     * @see #setMinimum
     * @see BoundedRangeModel#getMinimum
     */
    public int getMinimum()
    {
        return slider.getMinimum();
    }

    /**
     * Sets the slider's minimum value to {@code minimum}.  This method
     * forwards the new minimum value to the model.
     * <p>
     * The data model (an instance of {@code BoundedRangeModel})
     * handles any mathematical
     * issues arising from assigning faulty values.  See the
     * {@code BoundedRangeModel} documentation for details.
     * <p>
     * If the new minimum value is different from the previous minimum value,
     * all change listeners are notified.
     *
     * @param minimum the new minimum
     *
     * @beaninfo bound: true
     * preferred: true
     * description: The sliders minimum value.
     * @see #getMinimum
     * @see #addChangeListener
     * @see BoundedRangeModel#setMinimum
     */
    public void setMinimum(int minimum)
    {
        slider.setMinimum(minimum);
    }

    /**
     * Returns the maximum value supported by the slider
     * from the <code>BoundedRangeModel</code>.
     *
     * @return the value of the model's maximum property
     * @see #setMaximum
     * @see BoundedRangeModel#getMaximum
     */
    public int getMaximum()
    {
        return slider.getMaximum();
    }

    /**
     * Sets the slider's maximum value to {@code maximum}.  This method
     * forwards the new maximum value to the model.
     * <p>
     * The data model (an instance of {@code BoundedRangeModel})
     * handles any mathematical
     * issues arising from assigning faulty values.  See the
     * {@code BoundedRangeModel} documentation for details.
     * <p>
     * If the new maximum value is different from the previous maximum value,
     * all change listeners are notified.
     *
     * @param maximum the new maximum
     *
     * @beaninfo bound: true
     * preferred: true
     * description: The sliders maximum value.
     * @see #getMaximum
     * @see #addChangeListener
     * @see BoundedRangeModel#setMaximum
     */
    public void setMaximum(int maximum)
    {
        slider.setMaximum(maximum);
    }

    /**
     * Returns the {@code valueIsAdjusting} property from the model.  For
     * details on how this is used, see the {@code setValueIsAdjusting}
     * documentation.
     *
     * @return the value of the model's {@code valueIsAdjusting} property
     * @see #setValueIsAdjusting
     */
    public boolean getValueIsAdjusting()
    {
        return slider.getValueIsAdjusting();
    }

    /**
     * Sets the model's {@code valueIsAdjusting} property.  Slider look and
     * feel implementations should set this property to {@code true} when
     * a knob drag begins, and to {@code false} when the drag ends.
     *
     * @param b the new value for the {@code valueIsAdjusting} property
     *
     * @beaninfo expert: true
     * description: True if the slider knob is being dragged.
     * @see #getValueIsAdjusting
     * @see BoundedRangeModel#setValueIsAdjusting
     */
    public void setValueIsAdjusting(boolean b)
    {
        slider.setValueIsAdjusting(b);
    }

    /**
     * Returns the "extent" from the <code>BoundedRangeModel</code>.
     * This represents the range of values "covered" by the knob.
     *
     * @return an int representing the extent
     * @see #setExtent
     * @see BoundedRangeModel#getExtent
     */
    public int getExtent()
    {
        return slider.getExtent();
    }

    /**
     * Sets the size of the range "covered" by the knob.  Most look
     * and feel implementations will change the value by this amount
     * if the user clicks on either side of the knob.  This method just
     * forwards the new extent value to the model.
     * <p>
     * The data model (an instance of {@code BoundedRangeModel})
     * handles any mathematical
     * issues arising from assigning faulty values.  See the
     * {@code BoundedRangeModel} documentation for details.
     * <p>
     * If the new extent value is different from the previous extent value,
     * all change listeners are notified.
     *
     * @param extent the new extent
     *
     * @beaninfo expert: true
     * description: Size of the range covered by the knob.
     * @see #getExtent
     * @see BoundedRangeModel#setExtent
     */
    public void setExtent(int extent)
    {
        slider.setExtent(extent);
    }

    /**
     * Return this slider's vertical or horizontal orientation.
     *
     * @return {@code SwingConstants.VERTICAL} or
     * {@code SwingConstants.HORIZONTAL}
     * @see #setOrientation
     */
    public int getOrientation()
    {
        return slider.getOrientation();
    }

    /**
     * Set the slider's orientation to either {@code SwingConstants.VERTICAL} or
     * {@code SwingConstants.HORIZONTAL}.
     *
     * @param orientation {@code HORIZONTAL} or {@code VERTICAL}
     *
     * @throws IllegalArgumentException if orientation is not one of {@code VERTICAL}, {@code HORIZONTAL}
     * @beaninfo preferred: true
     * bound: true
     * attribute: visualUpdate true
     * description: Set the scrollbars orientation to either VERTICAL or HORIZONTAL.
     * enum: VERTICAL JSlider.VERTICAL
     * HORIZONTAL JSlider.HORIZONTAL
     * @see #getOrientation
     */
    public void setOrientation(int orientation)
    {
        slider.setOrientation(orientation);
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.6
     */
    public void setFont(Font font)
    {
        super.setFont(font);

        if (slider == null) {
            initSlider(JSlider.HORIZONTAL, 0, 100, 50);
        }

        slider.setFont(font);
    }

    /**
     * Set the font of the value field.
     *
     * @param font the desired Font for the value field
     */
    public void setValueFieldFont(Font font)
    {
        if (valueField == null) {
            initLabel();
        }

        valueField.setFont(font);
    }

    /**
     * {@inheritDoc}
     *
     * @since 1.7
     */
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h)
    {
        super.imageUpdate(img, infoflags, x, y, w, h);
        return slider.imageUpdate(img, infoflags, x, y, w, h);
    }

    /**
     * Returns the dictionary of what labels to draw at which values.
     *
     * @return the <code>Dictionary</code> containing labels and
     * where to draw them
     */
    public Dictionary getLabelTable()
    {
        return slider.getLabelTable();
    }

    /**
     * Used to specify what label will be drawn at any given value.
     * The key-value pairs are of this format:
     * <code>{ Integer value, java.swing.JComponent label }</code>.
     * <p>
     * An easy way to generate a standard table of value labels is by using the
     * {@code createStandardLabels} method.
     * <p>
     * Once the labels have been set, this method calls updateLabelUIs.
     * Note that the labels are only painted if the {@code paintLabels}
     * property is {@code true}.
     *
     * @param labels new {@code Dictionary} of labels, or {@code null} to
     *               remove all labels
     *
     * @beaninfo hidden: true
     * bound: true
     * attribute: visualUpdate true
     * description: Specifies what labels will be drawn for any given value.
     * @see #createStandardLabels(int)
     * @see #getLabelTable
     * @see #setPaintLabels
     */
    public void setLabelTable(Dictionary labels)
    {
        slider.setLabelTable(labels);
    }

    /**
     * Creates a {@code Hashtable} of numerical text labels, starting at the
     * slider minimum, and using the increment specified.
     * For example, if you call <code>createStandardLabels( 10 )</code>
     * and the slider minimum is zero,
     * then labels will be created for the values 0, 10, 20, 30, and so on.
     * <p>
     * For the labels to be drawn on the slider, the returned {@code Hashtable}
     * must be passed into {@code setLabelTable}, and {@code setPaintLabels}
     * must be set to {@code true}.
     * <p>
     * For further details on the makeup of the returned {@code Hashtable}, see
     * the {@code setLabelTable} documentation.
     *
     * @param increment distance between labels in the generated hashtable
     *
     * @return a new {@code Hashtable} of labels
     * @throws IllegalArgumentException if {@code increment} is less than or
     *                                  equal to zero
     * @see #setLabelTable
     * @see #setPaintLabels
     */
    public Hashtable createStandardLabels(int increment)
    {
        return createStandardLabels(increment, getMinimum());
    }

    /**
     * Creates a {@code Hashtable} of numerical text labels, starting at the
     * starting point specified, and using the increment specified.
     * For example, if you call
     * <code>createStandardLabels( 10, 2 )</code>,
     * then labels will be created for the values 2, 12, 22, 32, and so on.
     * <p>
     * For the labels to be drawn on the slider, the returned {@code Hashtable}
     * must be passed into {@code setLabelTable}, and {@code setPaintLabels}
     * must be set to {@code true}.
     * <p>
     * For further details on the makeup of the returned {@code Hashtable}, see
     * the {@code setLabelTable} documentation.
     *
     * @param increment distance between labels in the generated hashtable
     * @param start     value at which the labels will begin
     *
     * @return a new {@code Hashtable} of labels
     * @throws IllegalArgumentException if {@code start} is
     *                                  out of range, or if {@code increment} is less than or equal
     *                                  to zero
     * @see #setLabelTable
     * @see #setPaintLabels
     */
    public Hashtable createStandardLabels(int increment, int start)
    {
        return slider.createStandardLabels(increment, start);
    }

    /**
     * Returns true if the value-range shown for the slider is reversed,
     *
     * @return true if the slider values are reversed from their normal order
     * @see #setInverted
     */
    public boolean getInverted()
    {
        return slider.getInverted();
    }

    /**
     * Specify true to reverse the value-range shown for the slider and false to
     * put the value range in the normal order.  The order depends on the
     * slider's <code>ComponentOrientation</code> property.  Normal (non-inverted)
     * horizontal sliders with a <code>ComponentOrientation</code> value of
     * <code>LEFT_TO_RIGHT</code> have their maximum on the right.
     * Normal horizontal sliders with a <code>ComponentOrientation</code> value of
     * <code>RIGHT_TO_LEFT</code> have their maximum on the left.  Normal vertical
     * sliders have their maximum on the top.  These labels are reversed when the
     * slider is inverted.
     * <p>
     * By default, the value of this property is {@code false}.
     *
     * @param b true to reverse the slider values from their normal order
     *
     * @beaninfo bound: true
     * attribute: visualUpdate true
     * description: If true reverses the slider values from their normal order
     */
    public void setInverted(boolean b)
    {
        slider.setInverted(b);
    }

    /**
     * This method returns the major tick spacing.  The number that is returned
     * represents the distance, measured in values, between each major tick mark.
     * If you have a slider with a range from 0 to 50 and the major tick spacing
     * is set to 10, you will get major ticks next to the following values:
     * 0, 10, 20, 30, 40, 50.
     *
     * @return the number of values between major ticks
     * @see #setMajorTickSpacing
     */
    public int getMajorTickSpacing()
    {
        return slider.getMajorTickSpacing();
    }

    /**
     * This method sets the major tick spacing.  The number that is passed in
     * represents the distance, measured in values, between each major tick mark.
     * If you have a slider with a range from 0 to 50 and the major tick spacing
     * is set to 10, you will get major ticks next to the following values:
     * 0, 10, 20, 30, 40, 50.
     * <p>
     * In order for major ticks to be painted, {@code setPaintTicks} must be
     * set to {@code true}.
     * <p>
     * This method will also set up a label table for you.
     * If there is not already a label table, and the major tick spacing is
     * {@code > 0}, and {@code getPaintLabels} returns
     * {@code true}, a standard label table will be generated (by calling
     * {@code createStandardLabels}) with labels at the major tick marks.
     * For the example above, you would get text labels: "0",
     * "10", "20", "30", "40", "50".
     * The label table is then set on the slider by calling
     * {@code setLabelTable}.
     *
     * @param n new value for the {@code majorTickSpacing} property
     *
     * @beaninfo bound: true
     * attribute: visualUpdate true
     * description: Sets the number of values between major tick marks.
     * @see #getMajorTickSpacing
     * @see #setPaintTicks
     * @see #setLabelTable
     * @see #createStandardLabels(int)
     */
    public void setMajorTickSpacing(int n)
    {
        slider.setMajorTickSpacing(n);
    }

    /**
     * This method returns the minor tick spacing.  The number that is returned
     * represents the distance, measured in values, between each minor tick mark.
     * If you have a slider with a range from 0 to 50 and the minor tick spacing
     * is set to 10, you will get minor ticks next to the following values:
     * 0, 10, 20, 30, 40, 50.
     *
     * @return the number of values between minor ticks
     * @see #getMinorTickSpacing
     */
    public int getMinorTickSpacing()
    {
        return slider.getMinorTickSpacing();
    }

    /**
     * This method sets the minor tick spacing.  The number that is passed in
     * represents the distance, measured in values, between each minor tick mark.
     * If you have a slider with a range from 0 to 50 and the minor tick spacing
     * is set to 10, you will get minor ticks next to the following values:
     * 0, 10, 20, 30, 40, 50.
     * <p>
     * In order for minor ticks to be painted, {@code setPaintTicks} must be
     * set to {@code true}.
     *
     * @param n new value for the {@code minorTickSpacing} property
     *
     * @beaninfo bound: true
     * attribute: visualUpdate true
     * description: Sets the number of values between minor tick marks.
     * @see #getMinorTickSpacing
     * @see #setPaintTicks
     */
    public void setMinorTickSpacing(int n)
    {
        slider.setMinorTickSpacing(n);
    }

    /**
     * Returns true if the knob (and the data value it represents)
     * resolve to the closest tick mark next to where the user
     * positioned the knob.
     *
     * @return true if the value snaps to the nearest tick mark, else false
     * @see #setSnapToTicks
     */
    public boolean getSnapToTicks()
    {
        return slider.getSnapToTicks();
    }

    /**
     * Specifying true makes the knob (and the data value it represents)
     * resolve to the closest tick mark next to where the user
     * positioned the knob.
     * By default, this property is {@code false}.
     *
     * @param b true to snap the knob to the nearest tick mark
     *
     * @beaninfo bound: true
     * description: If true snap the knob to the nearest tick mark.
     * @see #getSnapToTicks
     */
    public void setSnapToTicks(boolean b)
    {
        slider.setSnapToTicks(b);
    }

    /**
     * Tells if tick marks are to be painted.
     *
     * @return true if tick marks are painted, else false
     * @see #setPaintTicks
     */
    public boolean getPaintTicks()
    {
        return slider.getPaintTicks();
    }

    /**
     * Determines whether tick marks are painted on the slider.
     * By default, this property is {@code false}.
     *
     * @param b whether or not tick marks should be painted
     *
     * @beaninfo bound: true
     * attribute: visualUpdate true
     * description: If true tick marks are painted on the slider.
     * @see #getPaintTicks
     */
    public void setPaintTicks(boolean b)
    {
        slider.setPaintTicks(b);
    }

    /**
     * Tells if the track (area the slider slides in) is to be painted.
     *
     * @return true if track is painted, else false
     * @see #setPaintTrack
     */
    public boolean getPaintTrack()
    {
        return slider.getPaintTrack();
    }

    /**
     * Determines whether the track is painted on the slider.
     * By default, this property is {@code true}.
     *
     * @param b whether or not to paint the slider track
     *
     * @beaninfo bound: true
     * attribute: visualUpdate true
     * description: If true, the track is painted on the slider.
     * @see #getPaintTrack
     */
    public void setPaintTrack(boolean b)
    {
        slider.setPaintTrack(b);
    }

    /**
     * Tells if labels are to be painted.
     *
     * @return true if labels are painted, else false
     * @see #setPaintLabels
     */
    public boolean getPaintLabels()
    {
        return slider.getPaintLabels();
    }

    /**
     * Determines whether labels are painted on the slider.
     * <p>
     * This method will also set up a label table for you.
     * If there is not already a label table, and the major tick spacing is
     * {@code > 0},
     * a standard label table will be generated (by calling
     * {@code createStandardLabels}) with labels at the major tick marks.
     * The label table is then set on the slider by calling
     * {@code setLabelTable}.
     * <p>
     * By default, this property is {@code false}.
     *
     * @param b whether or not to paint labels
     *
     * @beaninfo bound: true
     * attribute: visualUpdate true
     * description: If true labels are painted on the slider.
     * @see #getPaintLabels
     * @see #getLabelTable
     * @see #createStandardLabels(int)
     */
    public void setPaintLabels(boolean b)
    {
        slider.setPaintLabels(b);
    }
}
