CSC435 Web Services Assignment 6
Description of the project can be found in the INSTRUCTIONS.md file.

This assignment is an extension of the previous assignments but written using Dropwizard.

Dropwizard creates an H2 instance, and writes/reads directly from that file.

This was chosen because the DB doesn't get removed after shutdown and just updates when needed since the domain classes don't have to be changed.