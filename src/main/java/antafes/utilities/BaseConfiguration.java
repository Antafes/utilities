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

package antafes.utilities;

import lombok.AccessLevel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Base configuration
 */
public abstract class BaseConfiguration implements ConfigurationInterface {
    @Getter(AccessLevel.PROTECTED)
    private final Properties properties = new Properties();
    private final File propertiesFile = new File(this.getBasePath() + "gui.xml");

    /**
     * load all saved properties
     */
    @Override
    public void loadProperties()
    {
        if (this.propertiesFile.exists())
        {
            try
            {
                try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(this.propertiesFile))) {
                    this.properties.loadFromXML(inputStream);
                }
            }
            catch (IOException ignored)
            {}
        }
        else
        {
            this.properties.setProperty("openDirPath", new File(this.getHomeDirectory()).getPath());
            this.properties.setProperty("saveDirPath", new File(this.getHomeDirectory()).getPath());
        }
    }

    /**
     * save all properties
     */
    @Override
    public void saveProperties()
    {
        BufferedOutputStream outputStream;
        try
        {
            if (!this.propertiesFile.exists())
            {
                File path = new File(this.propertiesFile.getParent());

                if (!path.exists()) {
                    //noinspection ResultOfMethodCallIgnored
                    path.mkdirs();
                }

                //noinspection ResultOfMethodCallIgnored
                this.propertiesFile.createNewFile();
            }

            outputStream = new BufferedOutputStream(new FileOutputStream(this.propertiesFile));
            this.properties.storeToXML(outputStream, null);
        }
        catch (IOException ignored)
        {}
    }

    /**
     * Get the "open" dir path.
     *
     * @return File object for the "open" dir path
     */
    @Override
    public File getOpenDirPath()
    {
        return new File(this.properties.getProperty("openDirPath"));
    }

    /**
     * Get the save dir path including the new file.
     *
     * @param filename The file to save
     *
     * @return File object for the file that should be saved
     */
    @Override
    public File getSaveDirPath(String filename)
    {
        if (!filename.endsWith(".xml")) {
            filename += ".xml";
        }

        return new File(this.properties.getProperty("saveDirPath") + "/" + filename);
    }

    /**
     * Get the save dir path.
     *
     * @return A path
     */
    @Override
    public File getSaveDirPath()
    {
        return new File(this.properties.getProperty("saveDirPath"));
    }

    /**
     * Get the windows location on screen.
     * This will default to 0:0 if no position is saved.
     *
     * @return A Point object with the x and y coordinates.
     */
    @Override
    public Point getWindowLocation()
    {
        Point point = new Point(0, 0);
        String pointX = this.properties.getProperty("windowLocationX");
        String pointY = this.properties.getProperty("windowLocationY");

        if (pointX != null && !pointX.isEmpty() && pointY != null && !pointY.isEmpty())
        {
            point.setLocation(Double.parseDouble(pointX), Double.parseDouble(pointY));
        }

        return point;
    }

    /**
     * Get the extended state of the window. This correlates to the states from JFrame.
     * If nothing is found in the properties object JFrame.NORMAL is returned.
     *
     * @return State according to JFrame
     */
    @Override
    public int getExtendedState()
    {
        if (this.properties.getProperty("extendedState") == null || this.properties.getProperty("extendedState").equals("")) {
            this.setExtendedState(JFrame.NORMAL);
        }

        return Integer.parseInt(this.properties.getProperty("extendedState"));
    }

    /**
     * Set the open dir path.
     *
     * @param path The path used for opening files
     */
    @Override
    public void setOpenDirPath(String path)
    {
        if (new File(path).isFile()) {
            path = new File(path).getParent();
        }

        this.properties.setProperty("openDirPath", path);
    }

    /**
     * Set the save dir path.
     *
     * @param path The path used for saving files
     */
    @Override
    public void setSaveDirPath(String path)
    {
        if (new File(path).isFile())
            path = new File(path).getParent();

        this.properties.setProperty("saveDirPath", path);
    }

    /**
     * Set the windows position on the screen.
     *
     * @param point Position of the window
     */
    @Override
    public void setWindowLocation(Point point)
    {
        this.properties.setProperty("windowLocationX", String.valueOf(point.getX()));
        this.properties.setProperty("windowLocationY", String.valueOf(point.getY()));
    }

    /**
     * Set the extended state of the window.
     *
     * @param extendedState State according to JFrame
     */
    @Override
    public void setExtendedState(int extendedState)
    {
        this.properties.setProperty("extendedState", Integer.toString(extendedState));
    }

    /**
     * Set the selected language.
     *
     * @param language The language that has been selected.
     */
    @Override
    public void setLanguage(LanguageInterface language) {
        properties.setProperty("language", language.toString());
    }

    /**
     * Get the selected language.
     *
     * @return Language enum of the selected language
     */
    public LanguageInterface getLanguage()
    {
        return LanguageInterface.valueOf(this.properties.getProperty("language"));
    }

    /**
     * Get a language object from the currently selected language.
     *
     * @return Language object fetched from the enum of the currently selected language
     */
    public antafes.utilities.language.LanguageInterface getLanguageObject()
    {
        antafes.utilities.language.LanguageInterface language = null;

        try {
            language = (antafes.utilities.language.LanguageInterface) Class.forName(this.getLanguage().getLanguageString()).newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }

        return language;
    }
}