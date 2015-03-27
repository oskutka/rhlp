
function Restaurant(id, name, url, collapsed){
	this.id = id;
	this.name = name;
	this.url = url;
	this.collapsed = collapsed;
	this.isCollapsed = function() {
		return (localStorage["cz.redhat.oskutka.rhlp." + this.id + ".collapsed"] != "false");
	};

};

dayOfWeek = new Date().getDay();

// TODO this really cannot be any prettier?
function createRangeSelector(prefix, begin, end) {
	var cssSelector = "";
	for (var i = begin; i <= end ; i++) {
		if (i > begin) {
			cssSelector += ", ";
		}
		cssSelector += prefix + ":nth-child(" + i + ")";
	}
	return cssSelector;
}

var restaurants = new Array();
restaurants[restaurants.length] = (new Restaurant("kanas_restaurace", "Kanas - Restaurace", "Kanas #tab1 *:nth-child(n+2)"));
restaurants[restaurants.length] = (new Restaurant("kanas_jidelna", "Kanas - Jídelna", "Kanas #tab2 *:nth-child(n+2)"));
restaurants[restaurants.length] = (new Restaurant("paladeo", "Paladeo", "Paladeo ul:nth-of-type(" + dayOfWeek + ")"));
restaurants[restaurants.length] = (new Restaurant("purkynka", "Purkyňka", "Purkynka " + createRangeSelector(".moz-text-html div", (1+(dayOfWeek-1)*6),  (5+(dayOfWeek-1)*6) )));
restaurants[restaurants.length] = (new Restaurant("opice", "U 3 Opic", "Opice #menu>p:not(.dots,#menuTyden)"));
restaurants[restaurants.length] = (new Restaurant("sporthotel", "A-Sport Hotel", "Sporthotel " + createRangeSelector("table.tmaint tr", ((dayOfWeek-1)*8 + 2), (dayOfWeek)*8)));
restaurants[restaurants.length] = (new Restaurant("kotelna", "U Kotelny", "Kotelna .tmi-group"));
restaurants[restaurants.length] = (new Restaurant("prometheus", "Prometheus", "Prometheus div.panel table:nth-of-type(" + (dayOfWeek+1) + ")"));
restaurants[restaurants.length] = (new Restaurant("vista", "Hotel Vista", "Vista .tmi-group"));

function isTouchDevice() {
	return ('ontouchstart' in document.documentElement);
}

function getRestaurant(id) {
	for (var restaurant of restaurants) {
		if (restaurant.id == id) {
			return restaurant;
		};
	}
	return null;
	// TODO handle error
}


function isLocalStorageOk() {
	storageOk = true;
	for (var i = 0; i < restaurants.length; i++) {
		isStored = false;
		hasOrder = false;
		for (var j = 0; j < restaurants.length; j++) {
			if (localStorage["cz.redhat.oskutka.rhlp.order." + i + ".id"] == restaurants[j].id) {
				isStored = true;
			};
			if (localStorage["cz.redhat.oskutka.rhlp.order." + j + ".id"] == restaurants[i].id) {
				hasOrder = true;
			};
		};
		if (!isStored || !hasOrder) {
			storageOk = false;
		};
	}
	return storageOk;
}

function restoreLocalStorage() {
	// restore storage
    for (var i = 0; i < restaurants.length; i++) {
    	localStorage["cz.redhat.oskutka.rhlp.order." + i + ".id"] = restaurants[i].id;
    };
};

function checkLocalStorage() {
	if (!isLocalStorageOk()) {
		restoreLocalStorage();
	};
};
