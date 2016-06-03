# hdf-java

This is a fork of the [HDF-Java library](https://www.hdfgroup.org/products/java/) version 2.11.0.
The base library is unmodified. The fork adds a dynamic binary dependency loader and Maven artifact
publication support. Theses two features allow downstream code to add HDF support with a single,
regular Maven dependency.

The CCT core uses HDF to save and restore compute graphs and neural network weights.

If you're new to CCT, start with the [tutorial](https://github.com/hpe-cct/cct-tutorial).
