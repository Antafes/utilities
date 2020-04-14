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

import antafes.utilities.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CloseableTabbedPane extends JTabbedPane {
    private HashMap<Integer, JLabel> tabLabels;

    /**
     * Creates an empty <code>CloseableTabbedPane</code> with a default
     * tab placement of <code>JTabbedPane.TOP</code>.
     * @see #addTab
     */
    public CloseableTabbedPane() {
        this(TOP);
    }

    /**
     * Creates an empty <code>CloseableTabbedPane</code> with the specified tab placement
     * of either: <code>JTabbedPane.TOP</code>, <code>JTabbedPane.BOTTOM</code>,
     * <code>JTabbedPane.LEFT</code>, or <code>JTabbedPane.RIGHT</code>.
     *
     * @param tabPlacement the placement for the tabs relative to the content
     * @see #addTab
     */
    public CloseableTabbedPane(int tabPlacement) {
        this(tabPlacement, WRAP_TAB_LAYOUT);
    }

    /**
     * Creates an empty <code>CloseableTabbedPane</code> with the specified tab placement
     * and tab layout policy.  Tab placement may be either:
     * <code>JTabbedPane.TOP</code>, <code>JTabbedPane.BOTTOM</code>,
     * <code>JTabbedPane.LEFT</code>, or <code>JTabbedPane.RIGHT</code>.
     * Tab layout policy may be either: <code>JTabbedPane.WRAP_TAB_LAYOUT</code>
     * or <code>JTabbedPane.SCROLL_TAB_LAYOUT</code>.
     *
     * @param tabPlacement the placement for the tabs relative to the content
     * @param tabLayoutPolicy the policy for laying out tabs when all tabs will not fit on one run
     * @exception IllegalArgumentException if tab placement or tab layout policy are not
     *            one of the above supported values
     * @see #addTab
     * @since 1.4
     */
    public CloseableTabbedPane(int tabPlacement, int tabLayoutPolicy) {
        super(tabPlacement, tabLayoutPolicy);
    }

    /**
     * Add a close button for a tab.
     *
     * @param title Title of the tab
     * @param index Position of the tab
     */
    private void addTabCloseButtons(String title, int index) {
        JPanel panelTab = new JPanel(new GridBagLayout());
        panelTab.setOpaque(false);
        tabLabels.put(index, new JLabel(title));
        JButton closeButton = new JButton();
        Dimension buttonSize = new Dimension();
        buttonSize.setSize(12, 12);
        closeButton.setPreferredSize(buttonSize);
        ImageIcon icon = new ImageIcon(Utilities.getResourceInJar("images/x.png"));
        closeButton.setIcon(icon);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;

        panelTab.add(tabLabels.get(index), constraints);

        constraints.gridx++;
        constraints.weightx = 0;
        constraints.anchor = GridBagConstraints.EAST;
        constraints.insets = new Insets(0, 3, 0, 0);

        panelTab.add(closeButton, constraints);

        this.setTabComponentAt(index, panelTab);

        TabCloseActionHandler handler = new TabCloseActionHandler(
            this.getComponentAt(index),
            this
        );
        closeButton.addActionListener(handler);
    }

    /**
     * Inserts a new tab for the given component, at the given index,
     * represented by the given title and/or icon, either of which may
     * be {@code null}.
     *
     * @param title the title to be displayed on the tab
     * @param icon the icon to be displayed on the tab
     * @param component the component to be displayed when this tab is clicked.
     * @param tip the tooltip to be displayed for this tab
     * @param index the position to insert this new tab
     *       ({@code > 0 and <= getTabCount()})
     *
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code < 0 or > getTabCount()})
     *
     * @see #addTab
     * @see #removeTabAt
     */
    @Override
    public void insertTab(String title, Icon icon, Component component, String tip, int index) {
        super.insertTab(title, icon, component, tip, index);

        this.addTabCloseButtons(title, index);
    }

    /**
     * Sets the title at <code>index</code> to <code>title</code> which
     * can be <code>null</code>.
     * The title is not shown if a tab component for this tab was specified.
     * An internal exception is raised if there is no tab at that index.
     *
     * @param index the tab index where the title should be set
     * @param title the title to be displayed in the tab
     * @exception IndexOutOfBoundsException if index is out of range
     *            {@code (index < 0 || index >= tab count)}
     *
     * @see #getTitleAt
     * @see #setTabComponentAt
     * @beaninfo
     *    preferred: true
     *    attribute: visualUpdate true
     *  description: The title at the specified tab index.
     */
    @Override
    public void setTitleAt(int index, String title)
    {
        super.setTitleAt(index, title);
        tabLabels.get(index).setText(title);
    }

    /**
     * Close action handler.
     */
    private class TabCloseActionHandler implements ActionListener {
        private final Component tab;
        private final CloseableTabbedPane pane;

        /**
         * Constructor
         *
         * @param tab The tab that should be closed
         * @param pane The pane the tab resides in
         */
        public TabCloseActionHandler(Component tab, CloseableTabbedPane pane) {
            this.tab = tab;
            this.pane = pane;
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e Event object
         * @see #actionPerformed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            this.pane.remove(this.tab);
        }
    }
}
