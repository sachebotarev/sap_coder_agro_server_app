/**
 * 
 */
[
	{
		"shippingLocationKey": "1000A",
	    "description": "Ноздрачево",
	    "geoLocation": "A",
	    "locationType": "PRODUCING",
	    "region": "50",
	    "shipFromTransportations": [
	    	{
	    		"transportationNum": "100",
	    		"status": "049.ASSIGNED",
	    		"loadStartDateTime": 	"2018-01-01 00:00",
	    		"travelStartDateTime": 	"2018-01-01 00:10"
	    	}, {
	    		"transportationNum": "101",
	    		"status": "049.ASSIGNED",
	    		"loadStartDateTime": 	"2018-01-01 00:10",
	    		"travelStartDateTime": 	"2018-01-01 00:20"
	    	}, {
	    		"transportationNum": "102",
	    		"status": "049.ASSIGNED",
	    		"loadStartDateTime": 	"2018-01-01 00:20",
	    		"travelStartDateTime": 	"2018-01-01 00:30"
	    	}
	    ]
	}, {
		"shippingLocationKey": "1000B",
	    "description": "Producer 1",
	    "geoLocation": "B",
	    "locationType": "PRODUCING",
	    "region": "50",
	    "shipFromTransportations": []
	},
	{
		"shippingLocationKey": "1000C",
	    "description": "Producer 2",
	    "geoLocation": "C",
	    "locationType": "PRODUCING",
	    "region": "50",
	    "shipFromTransportations": []
	},
	{
		"shippingLocationKey": "2000A",
	    "description": "Шеховцово",
	    "geoLocation": "A",
	    "locationType": "STORAGE",
	    "region": "50",
	    "shipToTransportations": [
	    	{
	    		"transportationNum": "100",
	    		"status": "049.ASSIGNED",
	    		"unloadStartDateTime": 	"2018-01-01 01:00",
	    		"endDateTime": 			"2018-01-01 01:10"
	    	}, {
	    		"transportationNum": "101",
	    		"status": "049.ASSIGNED",
	    		"unloadStartDateTime": 	"2018-01-01 01:10",
	    		"endDateTime": 			"2018-01-01 01:20"
	    	}, {
	    		"transportationNum": "102",
	    		"status": "049.ASSIGNED",
	    		"unloadStartDateTime": 	"2018-01-01 01:31",
	    		"endDateTime": 			"2018-01-01 01:40"
	    	}
	    ]
	}, {
		"shippingLocationKey": "2000B",
	    "description": "Storage 2",
	    "geoLocation": "B",
	    "locationType": "STORAGE",
	    "region": "50",
	    "shipToTransportations": []
	}, {
		"shippingLocationKey": "2000C",
	    "description": "Storage 3",
	    "geoLocation": "C",
	    "locationType": "STORAGE",
	    "region": "50",
	    "shipToTransportations": []
	}
]