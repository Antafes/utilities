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

import javax.swing.*;
import java.awt.*;
import java.io.File;

public interface ConfigurationInterface {
    public void loadProperties();
    public void saveProperties();
    public String getBasePath();
    public File getOpenDirPath();
    public File getSaveDirPath(String filename);
    public File getSaveDirPath();
    public Point getWindowLocation();
    public int getExtendedState();
    public void setOpenDirPath(String path);
    public void setSaveDirPath(String path);
    public void setWindowLocation(Point point);
    public void setExtendedState(int extendedState);
    public antafes.utilities.language.LanguageInterface getLanguageObject();
    public LanguageInterface getLanguage();

    /**
     * Get the home directory of the user depending on the used system.
     *
     * @return Path to the home directory.
     */
    default public String getHomeDirectory()
    {
        if (System.getProperty("os.name").contains("Windows")) {
            return System.getProperty("user.home") + "/Documents/";
        }

        return System.getProperty("user.home");
    }

    public interface LanguageInterface {
        public String getLanguageString();
        public String getName();
        public ImageIcon getIcon();

        public static LanguageInterface valueOf(String name) {
            return null;
        }
    }
}
