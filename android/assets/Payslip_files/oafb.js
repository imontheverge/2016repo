/*=================================================================+
|               Copyright (c) 2011 Oracle Corporation              |
|                  Redwood Shores, California, USA                 |
|                       All rights reserved.                       |
+==================================================================+
| FILENAME                                                         |
|   oafb.js                                                        |
|                                                                  |
| HISTORY                                                          |
|   08-Mar-2011 skothe  Created.                                   |
+==================================================================*/
/* $Header: oafb.js 120.0.12010000.2 2012/03/07 10:52:35 lmodugul noship $ */

  document.documentElement.style.visibility='hidden';
  if (self == top) {
    document.documentElement.style.visibility='visible';
  } else {
      try
      {
        // In case of cross domain this would raise error
        // so frame should be busted
        var topLoc = top.location.href;
        document.documentElement.style.visibility='visible';       
      }
      catch(err)
      {        
        top.location=self.location;
      }
  }
