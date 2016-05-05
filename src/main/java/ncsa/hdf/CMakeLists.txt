cmake_minimum_required (VERSION 2.8.10)
PROJECT (HDFJAVA_NCSA_HDF)

add_subdirectory (${HDFJAVA_NCSA_HDF_SOURCE_DIR}/hdflib      hdflib)
add_subdirectory (${HDFJAVA_NCSA_HDF_SOURCE_DIR}/hdf5lib     hdf5lib)
add_subdirectory (${HDFJAVA_NCSA_HDF_SOURCE_DIR}/object      object)
add_subdirectory (${HDFJAVA_NCSA_HDF_SOURCE_DIR}/view        view)
