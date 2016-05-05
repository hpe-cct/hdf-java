cmake_minimum_required (VERSION 2.8.10)
PROJECT (HDFJAVA_NCSA_HDF_HDF5 Java)

set (CMAKE_VERBOSE_MAKEFILE 1)

INCLUDE_DIRECTORIES (
    ${HDFJAVA_NCSA_HDF_HDF5_SOURCE_DIR}
    ${HDFJAVA_NCSA_HDF_HDF5_BINARY_DIR}
    ${HDFJAVA_LIB_DIR}
    ${CMAKE_CURRENT_BINARY_DIR}/CMakeFiles/${HDFJAVA_NCSA_HDF5_LIB_CORENAME}.dir/ncsa/hdf/hdf5lib
)

SET_GLOBAL_VARIABLE (HDFJAVA_SOURCE_PACKAGES
    "${HDFJAVA_SOURCE_PACKAGES};ncsa.hdf.hdf5lib.callbacks;ncsa.hdf.hdf5lib.exceptions;ncsa.hdf.hdf5lib.structs;ncsa.hdf.hdf5lib"
)

set (HDFJAVA_NCSA_HDF_HDF5_CALLBACKS_SRCS
    callbacks/H5D_iterate_cb.java
    callbacks/H5D_iterate_t.java
    callbacks/H5L_iterate_cb.java
    callbacks/H5L_iterate_t.java
    callbacks/H5O_iterate_cb.java
    callbacks/H5O_iterate_t.java
    callbacks/Callbacks.java
)

set (HDFJAVA_NCSA_HDF_HDF5_EXCEPTIONS_SRCS
    exceptions/HDF5Exception.java
    exceptions/HDF5AtomException.java
    exceptions/HDF5AttributeException.java
    exceptions/HDF5BtreeException.java
    exceptions/HDF5DataFiltersException.java
    exceptions/HDF5DatasetInterfaceException.java
    exceptions/HDF5DataspaceInterfaceException.java
    exceptions/HDF5DataStorageException.java
    exceptions/HDF5DatatypeInterfaceException.java
    exceptions/HDF5ExternalFileListException.java
    exceptions/HDF5FileInterfaceException.java
    exceptions/HDF5FunctionArgumentException.java
    exceptions/HDF5FunctionEntryExitException.java
    exceptions/HDF5HeapException.java
    exceptions/HDF5InternalErrorException.java
    exceptions/HDF5JavaException.java
    exceptions/HDF5LibraryException.java
    exceptions/HDF5LowLevelIOException.java
    exceptions/HDF5MetaDataCacheException.java
    exceptions/HDF5ObjectHeaderException.java
    exceptions/HDF5PropertyListInterfaceException.java
    exceptions/HDF5ReferenceException.java
    exceptions/HDF5ResourceUnavailableException.java
    exceptions/HDF5SymbolTableException.java
)

set (HDFJAVA_NCSA_HDF_HDF5_STRUCTS_SRCS
    structs/H5_ih_info_t.java
    structs/H5A_info_t.java
    structs/H5AC_cache_config_t.java
    structs/H5G_info_t.java
    structs/H5L_info_t.java
    structs/H5O_hdr_info_t.java
    structs/H5O_info_t.java
)

set (HDFJAVA_NCSA_HDF_HDF5_SRCS
    HDFArray.java
    HDF5Constants.java
    HDF5GroupInfo.java
    HDFNativeData.java
    H5.java
)

set (CMAKE_JNI_TARGET TRUE)

file (WRITE ${PROJECT_BINARY_DIR}/Manifest.txt
"
"
)

set (CMAKE_JAVA_INCLUDE_PATH "${HDFJAVA_LOGGING_JAR}")

add_jar (${HDFJAVA_NCSA_HDF5_LIB_TARGET} OUTPUT_NAME "${HDFJAVA_NCSA_HDF5_LIB_TARGET}-${HDFJAVA_PACKAGE_VERSION}" OUTPUT_DIR ${CMAKE_JAVA_TARGET_OUTPUT_DIR} MANIFEST ${PROJECT_BINARY_DIR}/Manifest.txt ${HDFJAVA_NCSA_HDF_HDF5_CALLBACKS_SRCS} ${HDFJAVA_NCSA_HDF_HDF5_EXCEPTIONS_SRCS} ${HDFJAVA_NCSA_HDF_HDF5_STRUCTS_SRCS} ${HDFJAVA_NCSA_HDF_HDF5_SRCS})
install_jar (${HDFJAVA_NCSA_HDF5_LIB_TARGET} ${HJAVA_INSTALL_JAR_DIR} libraries)
#if (NOT WIN32)
#  install_jni_symlink (${HDFJAVA_NCSA_HDF5_LIB_TARGET} ${HJAVA_INSTALL_JAR_DIR} libraries)
#endif (NOT WIN32)

get_target_property (${HDFJAVA_NCSA_HDF5_LIB_TARGET}_JAR_FILE ${HDFJAVA_NCSA_HDF5_LIB_TARGET} JAR_FILE)
SET_GLOBAL_VARIABLE (HDFJAVA_JARS_TO_EXPORT "${HDFJAVA_JARS_TO_EXPORT};${${HDFJAVA_NCSA_HDF5_LIB_TARGET}_JAR_FILE}")
SET_GLOBAL_VARIABLE (HDFJAVA_HDF5_JARS ${${HDFJAVA_NCSA_HDF5_LIB_TARGET}_JAR_FILE})

add_dependencies (${HDFJAVA_NCSA_HDF5_LIB_TARGET} ${HDFJAVA_JNI_HDF5_LIB_TARGET})
