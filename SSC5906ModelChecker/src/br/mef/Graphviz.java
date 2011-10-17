package br.mef;

//Classe retirada da ferramenta JaBUTi

//GraphViz.java - a simple API to call dot from Java programs

/*$Id$*/
/*
******************************************************************************
*                                                                            *
*              (c) Copyright 2003 Laszlo Szathmary                           *
*                                                                            *
* This program is free software; you can redistribute it and/or modify it    *
* under the terms of the GNU Lesser General Public License as published by   *
* the Free Software Foundation; either version 2.1 of the License, or        *
* (at your option) any later version.                                        *
*                                                                            *
* This program is distributed in the hope that it will be useful, but        *
* WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY *
* or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public    *
* License for more details.                                                  *
*                                                                            *
* You should have received a copy of the GNU Lesser General Public License   *
* along with this program; if not, write to the Free Software Foundation,    *
* Inc., 675 Mass Ave, Cambridge, MA 02139, USA.                              *
*                                                                            *
******************************************************************************
*/

import java.io.*;

import javax.swing.JOptionPane;

/**
* <dl>
* <dt>Purpose: GraphViz Java API
* <dd>
*
* <dt>Description:
* <dd> With this Java class you can simply call dot
*      from your Java programs
* <dt>Example usage:
* <dd>
* <pre>
*    GraphViz gv = new GraphViz();
*    gv.addln(gv.start_graph());
*    gv.addln("A -> B;");
*    gv.addln("A -> C;");
*    gv.addln(gv.end_graph());
*    System.out.println(gv.getDotSource());
*
*    File out = new File("out.gif");
*    gv.writeGraphToFile(gv.getGraph(gv.getDotSource()), out);
* </pre>
* </dd>
*
* </dl>
*
* @version v0.1, 2003/12/04 (Decembre)
* @author  Laszlo Szathmary (<a href="szathml@delfin.unideb.hu">szathml@delfin.unideb.hu</a>)
*/
public class Graphviz
{
/**
* The dir where temporary files will be created.
*/
private static String TEMP_DIR   = System.getProperty("java.io.tmpdir");

/**
* Where is your dot program located? It will be called externally.
*/
private final static String DOT_W        = "c:\\Arquivos de programas\\ATT\\GraphViz\\bin\\dot.exe";
private final static String DOT_L        = "/usr/bin/dot";
private static String DOT = null;

/**
* The source of the graph written in dot language.
*/
	private StringBuffer graph = new StringBuffer();

	
/**
* Constructor: creates a new GraphViz object that will contain
* a graph.
* @throws FileNotFoundException 
*/
public Graphviz() throws FileNotFoundException {
	   if ( DOT == null )
	   {
		   String s = System.getProperty("os.name").toUpperCase();
		   if ( "LINUX".equals(s) )
		   {
			   DOT = DOT_L;
		   }
		   else
		   if ( s != null && s.startsWith("WINDOWS") )
		   {
			   DOT = DOT_W;
		   }
		   else
		   {
			   DOT = JOptionPane.showInputDialog(null, "Please enter path:", 
					   "Cannot find GraphViz layouter (dot).", JOptionPane.ERROR_MESSAGE);
			   
		   }
		   while ( DOT != null )
		   {
			   File f = new File(DOT);
			   if ( f.isFile() && f.canRead() )
				   break;
			   DOT = JOptionPane.showInputDialog(null, "Please enter path (ATT\\GraphViz\\bin\\dot.exe):", 
					   "Cannot find GraphViz layouter at " + DOT, JOptionPane.ERROR_MESSAGE);
			   
		   }
		   if ( DOT == null )
		   {
			   DOT = "";
			   throw new FileNotFoundException("Cannot find GraphViz.");
		   }
	   }
}

/**
* Returns the graph's source description in dot language.
* @return Source of the graph in dot language.
*/
public String getDotSource() {
  return graph.toString();
}

/**
* Adds a string to the graph's source (without newline).
*/
public void add(String line) {
  graph.append(line);
}

/**
* Adds a string to the graph's source (with newline).
*/
public void addln(String line) {
  graph.append(line+"\n");
}

/**
* Adds a newline to the graph's source.
*/
public void addln() {
  graph.append('\n');
}

/**
* Returns the graph as an image in binary format.
* @param dot_source Source of the graph to be drawn.
* @return A byte array containing the image of the graph.
*/
public byte[] getGraph(String dot_source)
{
  File dot;
  byte[] img_stream = null;

  try {
     dot = writeDotSourceToFile(dot_source);
     if (dot != null)
     {
        img_stream = get_img_stream(dot);
        if (dot.delete() == false) 
           System.err.println("Warning: "+dot.getAbsolutePath()+" could not be deleted!");
        return img_stream;
     }
     return null;
  } catch (java.io.IOException ioe) { return null; }
}

String getDotGraph(String dot_source)
{
  File dot;
  String img_stream = null;

  try {
     dot = writeDotSourceToFile(dot_source);
     if (dot != null)
     {
        img_stream = get_dot_stream(dot);
        if (dot.delete() == false) 
           System.err.println("Warning: "+dot.getAbsolutePath()+" could not be deleted!");
     }
     return img_stream;
  } catch (Exception ioe) { return null; }
}

/**
* Writes the graph's image in a file.
* @param img   A byte array containing the image of the graph.
* @param file  Name of the file to where we want to write.
* @return Success: 1, Failure: -1
*/
public int writeGraphToFile(byte[] img, String file)
{
  File to = new File(file);
  return writeGraphToFile(img, to);
}

/**
* Writes the graph's image in a file.
* @param img   A byte array containing the image of the graph.
* @param to    A File object to where we want to write.
* @return Success: 1, Failure: -1
*/
public int writeGraphToFile(byte[] img, File to)
{
  try {
     FileOutputStream fos = new FileOutputStream(to);
     fos.write(img);
     fos.close();
  } catch (java.io.IOException ioe) { return -1; }
  return 1;
}

/**
* It will call the external dot program, and return the image in
* binary format.
* @param dot Source of the graph (in dot language).
* @return The image of the graph in .gif format.
*/
private byte[] get_img_stream(File dot)
{
  File img;
  byte[] img_stream = null;

  try {
     img = File.createTempFile("graph_", ".gif", new File(this.TEMP_DIR));
     String temp = img.getAbsolutePath();

     Runtime rt = Runtime.getRuntime();
     String cmd = DOT + " -Tgif "+dot.getAbsolutePath()+" -o"+img.getAbsolutePath();
     Process p = rt.exec(cmd);
     p.waitFor();

     FileInputStream in = new FileInputStream(img.getAbsolutePath());
     img_stream = new byte[in.available()];
     in.read(img_stream);
     // Close it if we need to
     if( in != null ) in.close();

     if (img.delete() == false) 
        System.err.println("Warning: "+img.getAbsolutePath()+" could not be deleted!");
  }
  catch (java.io.IOException ioe) {
     System.err.println("Error:    in I/O processing of tempfile in dir "+this.TEMP_DIR+"\n");
     System.err.println("       or in calling external command");
     ioe.printStackTrace();
  }
  catch (java.lang.InterruptedException ie) {
     System.err.println("Error: the execution of the external program was interrupted");
     ie.printStackTrace();
  }

  return img_stream;
}

private String get_dot_stream(File dot) throws IOException, InterruptedException
{
  File img;

 img = File.createTempFile("graph_", ".dot", new File(TEMP_DIR));

 Runtime rt = Runtime.getRuntime();
 String cmd = DOT + " -Tdot "+dot.getAbsolutePath()+" -o"+img.getAbsolutePath();
 Process p = rt.exec(cmd);
 p.waitFor();
  return img.getAbsolutePath();
}


/**
* Writes the source of the graph in a file, and returns the written file
* as a File object.
* @param str Source of the graph (in dot language).
* @return The file (as a File object) that contains the source of the graph.
*/
private File writeDotSourceToFile(String str) throws java.io.IOException
{
  File temp;
  try {
     temp = File.createTempFile("graph_", ".dot.tmp", new File(this.TEMP_DIR));
     FileWriter fout = new FileWriter(temp);
     fout.write(str);
     fout.close();
  }
  catch (Exception e) {
     System.err.println("Error: I/O error while writing the dot source to temp file!");
     return null;
  }
  return temp;
}

/**
* Returns a string that is used to start a graph.
* @return A string to open a graph.
*/
public String start_graph() {
  return "digraph G {";
}

/**
* Returns a string that is used to end a graph.
* @return A string to close a graph.
*/
public String end_graph() {
  return "}";
}
}
