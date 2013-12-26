Current D3:
>> refer to bar chart - http://bl.ocks.org/mbostock/3887051
>>      -ve chart     - http://bl.ocks.org/mbostock/2368837
>>     concepts : http://bost.ocks.org/mike/bar/3/
>> single value mapping -- http://stackoverflow.com/questions/16514333/drawing-map-with-d3js-from-openstreetmap-geojson
>> web request using d3 -> https://github.com/mbostock/d3/wiki/Requests
>> find rating from Yelp API -> https://github.com/Yelp/yelp-api/blob/a46cc618a76a58b296f2f9e9916c76daa39882c1/v2/java/Yelp.java
   -- http://www.yelp.com/developers/documentation/v2/search_api#rValue
>> deal with json data 
  --  http://stackoverflow.com/questions/10086167/d3-how-to-deal-with-json-data-structures



>> d3 examples -> https://vida.io/explore
>> dynamic json data >>>>  http://pothibo.com/2013/09/d3-js-how-to-handle-dynamic-json-data/

http://localhost:8888/placeSearch.html

Current PolyChart :
>> http://www.polychartjs.com/demo?interactive

=======================================================
> https://www.dashingd3js.com/basic-building-blocks
 > http://nickqizhu.github.io/d3-cookbook/
 > http://nickqizhu.github.io/dc.js/vc/index.html
> http://square.github.io/crossfilter/

 > http://www.polychartjs.com/demo?interactive
 > https://github.com/Polychart/polychart2/wiki/pivot
 > http://www.polychartjs.com/demo?salaries

1. Concepts on scale 
  > http://bost.ocks.org/mike/simplify/
  > https://gist.github.com/mbostock/899711
  > http://bl.ocks.org/mbostock/899711
  > http://bl.ocks.org/mbostock/899670
http://bl.ocks.org/mbostock/899649
  > http://jsfiddle.net/uF9PV/7/
> https://github.com/mbostock/d3/wiki/Quantitative-Scales

2.  
  > https://github.com/mbostock/d3/wiki/Gallery
  > http://christopheviau.com/d3list/gallery.html
  > http://techslides.com/over-1000-d3-js-examples-and-demos/

3. 
 > http://bost.ocks.org/mike/leaflet/
 > http://www.kgryte.com/blog/tag/d3-js/
 > http://polymaps.org/docs/

----  http://paperjs.org/examples/tadpoles/
---- http://sigmajs.org/
--- http://arborjs.org/atlas/
--- http://www.jqplot.com/
--- http://g.raphaeljs.com/
--- http://datawrapper.de/
--- http://www.flotcharts.org/flot/examples/stacking/index.html

//////////////////////////



var placeinfo = [
			{
			  "key":"hospital",
			  "values": 
			  [{
			   "key":"El Camino",
			   "values":[2,-120.12,36.98]
			  },
			  {
			   "key":"PAMF",
			   "values":[5,-120.12,34.98]
			  }]
			},
			{
			  "key":"grocery_or_supermarket",
                          "values" :
                         [{
			   "key":"India  Cash n Carry",
			   "values":[10,-120.12,35.02]
			  }]
			}
		];


/////////////////////////////////



var placeinfo2 = [
			{
			  "key":"placeinfo",
			  "values": 
			  [{
			   "place_name":"El Camino",
                           "category" : "hospital",
                           "rating":2,
                           "lat":-120.12,
                           "long":36.98
			  },
			  {
			   "place_name":"PAMF",
                           "category" : "hospital",
                           "rating":2,
                           "lat":-120.12,
                           "long":35.98
			  },
                          {
			   "place_name":"India Cash n Carry",
                           "category" : "grocery",
                           "rating":8,
                           "lat":-120.12,
                           "long":34.98
			  }]
			}
		];