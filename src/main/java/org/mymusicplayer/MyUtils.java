/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymusicplayer;

import antlr.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
 *
 * @author preet
 */
public class MyUtils {
 
    public static String toHtml( String string )
  {
    if(string.equals("")||string==null )
      return "<html><body></body></html>";

    BufferedReader st = new BufferedReader( new StringReader( string ) );
    StringBuffer buf = new StringBuffer( "<html><body>" );

    try
    {
      String str = st.readLine();

      while( str != null )
      {
        if( str.equalsIgnoreCase( "<br/>" ) )
        {
          str = "<br>";
        }

        buf.append( str );

        if( !str.equalsIgnoreCase( "<br>" ) )
        {
          buf.append( "<br>" );
        }

        str = st.readLine();
      }
    }
    catch( IOException e )
    {
      e.printStackTrace();
    }

    buf.append( "</body></html>" );
    string = buf.toString();
    return string;
  }
}


