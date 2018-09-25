
google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawBasic);

function drawBasic() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', 'Year');
      data.addColumn('number', 'Cars');

      data.addRows([
        [1990,0],   [1991,1],  [1992,2],  [1993,3],  [1994,4],  [1995,5],
        [1996,6],  [1997,7],  [1998,20],  [1999,9],  [2000,10], [2001,11],
        [2002,12], [2003,13], [2004,14], [2005,20], [2006,16], [2007,17],
        [2008,18], [2009,19], [2010,2], [2011,21], [2012,22], [2013,23],
        [2014,24], [2015,25], [2016,26], [2017,28], [2018,29]
      ]);

      var options = {
        hAxis: {
          title: 'Year'
        },
        vAxis: {
          title: 'Number'
        }
      };

      var chart = new google.visualization.LineChart(document.getElementsByClassName('inner-container__stat')[0]);

      chart.draw(data, options);
    }