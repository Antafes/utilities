This is a small utilities library intended to simplify access to configurations and provide other utilities like comparators.

To fetch a basic Configuration object simply call ``ConfigurationFactory.getConfiguration()``. If you want to get your 
own Configuration based on the base one, you can call ``ConfigurationFactory.getConfiguration(MyConfiguration.class)``. 

Included comparators:
* ``StringComparator`` (Simple comparison of two strings)