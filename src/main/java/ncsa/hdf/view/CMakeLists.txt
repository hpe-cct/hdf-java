cmake_minimum_required (VERSION 2.8.10)
PROJECT (HDFJAVA_NCSA_HDF_VIEW)

set (CMAKE_VERBOSE_MAKEFILE 1)

INCLUDE_DIRECTORIES (
    ${JAVA_NCSA_HDF_HDF4_BINARY_DIR}
    ${JAVA_NCSA_HDF_HDF5_BINARY_DIR}
    ${JAVA_NCSA_HDF_OBJECT_BINARY_DIR}
    ${HDFJAVA_LIB_DIR}
)

set_global_variable (HDFJAVA_SOURCE_PACKAGES
    "${HDFJAVA_SOURCE_PACKAGES};ncsa.hdf.view"
)

set (HDFJAVA_NCSA_HDF_VIEW_SRCS
    Chart.java
    DataOptionDialog.java
    DataView.java
    DefaultFileFilter.java
    DefaultImageView.java
    DefaultMetaDataView.java
    DefaultPaletteView.java
    DefaultTableView.java
    DefaultTextView.java
    DefaultTreeView.java
    FileConversionDialog.java
    HDFView.java
    HelpView.java
    ImageView.java
    MathConversionDialog.java
    MetaDataView.java
    NewAttributeDialog.java
    NewDatasetDialog.java
    NewDatatypeDialog.java
    NewFileDialog.java
    NewGroupDialog.java
    NewImageDialog.java
    NewLinkDialog.java
    NewTableDataDialog.java
    PaletteView.java
    TableView.java
    TextView.java
    Tools.java
    TreeView.java
    UserOptionsDialog.java
    ViewManager.java
    ViewProperties.java
)
set (HDFJAVA_NCSA_HDF_VIEW_ICONS
    icons/animation.gif
    icons/apps.gif
    icons/audio.gif
    icons/autocontrast.gif
    icons/blank.gif
    icons/brightness.gif
    icons/chart.gif
    icons/copy.gif
    icons/cut.gif
    icons/dataset.gif
    icons/datasetA.gif
    icons/datatype.gif
    icons/datatypeA.gif
    icons/fileclose.gif
    icons/filenew.gif
    icons/fileopen.gif
    icons/filesave.gif
    icons/first.gif
    icons/folderclose.gif
    icons/foldercloseA.gif
    icons/folderopen.gif
    icons/folderopenA.gif
    icons/hdf_large.gif
    icons/hdf.gif
    icons/hdf32.gif
    icons/hdf32.icns
    icons/hdf32.png
    icons/hdf4.gif
    icons/hdf5.gif
    icons/help.gif
    icons/image.gif
    icons/imageA.gif
    icons/last.gif
    icons/link.gif
    icons/next.gif
    icons/palette.gif
    icons/paste.gif
    icons/pdf.gif
    icons/previous.gif
    icons/question.gif
    icons/table.gif
    icons/tableA.gif
    icons/text.gif
    icons/textA.gif
    icons/txt.gif
    icons/url.gif
    icons/video.gif
    icons/xls.gif
    icons/zoomin.gif
    icons/zoomout.gif
)

set (HDFJAVA_NCSA_HDF_VIEW_HTML
    NewAttrHelp.html
    NewDatsetHelp.html
)

INCLUDE_DIRECTORIES (${CMAKE_CURRENT_BINARY_DIR}/CMakeFiles/${HDFJAVA_NCSA_VIEW_LIB_CORENAME}.dir/ncsa/hdf/object)

foreach (HCP_JAR ${HDFJAVA_JARS_TO_EXPORT})
  get_filename_component (_HCP_FILE ${HCP_JAR} NAME)
  set (HDFVIEW_CLASSJARS "${_HCP_FILE} ${HDFVIEW_CLASSJARS}")
endforeach (HCP_JAR ${HDFJAVA_JARS_TO_EXPORT})
foreach (HCP_JAR ${HDFJAVA_HDFVIEWJAR_TO_EXPORT})
  get_filename_component (_HCP_FILE ${HCP_JAR} NAME)
  set (HDFVIEW_CLASSJARS "${_HCP_FILE} ${HDFVIEW_CLASSJARS}")
endforeach (HCP_JAR ${HDFJAVA_HDFVIEWJAR_TO_EXPORT})
get_filename_component (_HCP_FILE ${HDFJAVA_LOGGING_NOP_JAR} NAME)
set (HDFVIEW_CLASSJARS "${HDFVIEW_CLASSJARS} ${_HCP_FILE}")

file (WRITE ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/Manifest.txt
  "Main-Class: ncsa.hdf.view.HDFView
Class-Path: ${HDFVIEW_CLASSJARS}
"
)

set (CMAKE_JAVA_INCLUDE_PATH "${HDFJAVA_OBJECT_JARS};${HDFVIEW_OPTIONS_INCLUDE_PATH};${HDFJAVA_LOGGING_JAR}")

add_jar (${HDFJAVA_NCSA_VIEW_LIB_TARGET} OUTPUT_DIR ${CMAKE_JAVA_TARGET_OUTPUT_DIR} MANIFEST ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/Manifest.txt SOURCES "${HDFJAVA_NCSA_HDF_VIEW_SRCS};${HDFJAVA_NCSA_HDF_VIEW_ICONS}")
install_jar (${HDFJAVA_NCSA_VIEW_LIB_TARGET} ${HJAVA_INSTALL_JAR_DIR} apps)

get_target_property (${HDFJAVA_NCSA_VIEW_LIB_TARGET}_JAR_FILE ${HDFJAVA_NCSA_VIEW_LIB_TARGET} JAR_FILE)
set_global_variable (HDFJAVA_HDFVIEWJAR_TO_EXPORT "${HDFJAVA_HDFVIEWJAR_TO_EXPORT};${${HDFJAVA_NCSA_VIEW_LIB_TARGET}_JAR_FILE}")

add_dependencies (${HDFJAVA_NCSA_VIEW_LIB_TARGET} ${HDFJAVA_NCSA_OBJECT_LIB_TARGET})

if (UNIX)
  set (HDFVIEW_SCRIPT "hdfview.sh")
else (UNIX)
  set (HDFVIEW_SCRIPT "hdfview.bat")
endif (UNIX)

get_filename_component (HDFVIEW_JAR ${${HDFJAVA_NCSA_VIEW_LIB_TARGET}_JAR_FILE} NAME)
#get_filename_component (JAVA_PATH ${CMAKE_Java_RUNTIME} PATH)
if (WIN32)
  set (CMAKE_JAVA_INCLUDE_FLAG_SEP ";")
  set (SCRIPT_COMMAND_QUOTE "\"")
  set (JAVA_PATH "C:/Windows/System32")
else (WIN32)
  set (CMAKE_JAVA_INCLUDE_FLAG_SEP ":")
  get_filename_component (JAVA_PATH ${CMAKE_Java_RUNTIME} PATH)
endif (WIN32)

if (HDFJAVA_PACK_JRE)
  configure_file (
      ${HDFJAVA_NCSA_HDF_VIEW_SOURCE_DIR}/jre${HDFVIEW_SCRIPT}.cmake.in
      ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/${HDFVIEW_SCRIPT} @ONLY
  )
else (HDFJAVA_PACK_JRE)
  configure_file (
      ${HDFJAVA_NCSA_HDF_VIEW_SOURCE_DIR}/${HDFVIEW_SCRIPT}.cmake.in
      ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/${HDFVIEW_SCRIPT} @ONLY
  )
endif (HDFJAVA_PACK_JRE)

if (BUILD_TESTING)
  if (UNIX)
    set (HDFVIEW_TEST_SCRIPT "thdfview.sh")
  else (UNIX)
    set (HDFVIEW_TEST_SCRIPT "thdfview.bat")
  endif (UNIX)

  set (HDFVIEW_TEST_CLASSPATH "${CMAKE_RUNTIME_OUTPUT_DIRECTORY}/*${CMAKE_JAVA_INCLUDE_FLAG_SEP}${HDFJAVA_LIB_DIR}/*${CMAKE_JAVA_INCLUDE_FLAG_SEP}")

  file (WRITE ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/${HDFVIEW_TEST_SCRIPT}.cmake.in
    "cd ${CMAKE_TEST_OUTPUT_DIRECTORY}
${SCRIPT_COMMAND_QUOTE}${CMAKE_Java_RUNTIME} ${CMAKE_Java_RUNTIME_FLAGS}${SCRIPT_COMMAND_QUOTE} -Xmx1024M -Djava.library.path=${SCRIPT_COMMAND_QUOTE}${CMAKE_TEST_OUTPUT_DIRECTORY}${SCRIPT_COMMAND_QUOTE} -Dorg.slf4j.simpleLogger.defaultLogLevel=trace -Dhdfview.root=${HDFJAVA_BINARY_DIR} -cp ${SCRIPT_COMMAND_QUOTE}${HDFVIEW_TEST_CLASSPATH}${SCRIPT_COMMAND_QUOTE} ncsa.hdf.view.HDFView -root ${CMAKE_RUNTIME_OUTPUT_DIRECTORY} $*
cd ${HDFJAVA_BINARY_DIR}
    "
  )

  configure_file (
      ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/${HDFVIEW_TEST_SCRIPT}.cmake.in
      ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/${HDFVIEW_TEST_SCRIPT} @ONLY
  )
endif (BUILD_TESTING)
  
install (
    FILES ${HDFJAVA_NCSA_HDF_VIEW_BINARY_DIR}/${HDFVIEW_SCRIPT}
    DESTINATION ${HJAVA_INSTALL_BIN_DIR}
    COMPONENT apps
    PERMISSIONS OWNER_WRITE OWNER_READ OWNER_EXECUTE GROUP_READ GROUP_EXECUTE WORLD_READ WORLD_EXECUTE
)
