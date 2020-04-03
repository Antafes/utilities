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

public class ConfigurationFactory {
    private static ConfigurationInterface configuration;

    /**
     * Get the basic configuration object provided by this package.
     *
     * @return Basic configuration
     */
    public static ConfigurationInterface getConfiguration()
    {
        return getConfiguration(Configuration.class);
    }

    /**
     * Get the given configuration object.
     *
     * @param configuration The configuration object to create
     *
     * @return Given configuration object
     */
    public static ConfigurationInterface getConfiguration(Class<? extends ConfigurationInterface> configuration)
    {
        if (ConfigurationFactory.configuration == null) {
            try {
                ConfigurationFactory.configuration = configuration.newInstance();
            } catch (InstantiationException | IllegalAccessException ignored) {
                // This should never happen!
            }
        }

        return ConfigurationFactory.configuration;
    }
}
