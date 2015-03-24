
function Restaurant(id, name, url, collapsed){
	this.id = id;
	this.name = name;
	this.url = url;
	this.collapsed = collapsed;
	this.isCollapsed = function() {
		return (localStorage["cz.redhat.oskutka.rhlp." + this.id + ".collapsed"] == "true");
	};

};

dayOfWeek = new Date().getDay();

var restaurants = new Array();
restaurants[restaurants.length] = (new Restaurant("kanas_restaurace", "Kanas - Restaurace", "Kanas #tab1"));
restaurants[restaurants.length] = (new Restaurant("kanas_jidelna", "Kanas - Jídelna", "Kanas #tab2"));
restaurants[restaurants.length] = (new Restaurant("paladeo", "Paladeo", "Paladeo ul:nth-of-type(" + dayOfWeek + ")"));
restaurants[restaurants.length] = (new Restaurant("purkynka", "Purkyňka", "Purkynka .moz-text-html div:nth-child(" + (1+(dayOfWeek-1)*6) + "),.moz-text-html div:nth-child(" + (2+(dayOfWeek-1)*6) + "),.moz-text-html div:nth-child(" + (3+(dayOfWeek-1)*6) + "),.moz-text-html div:nth-child(" + (4+(dayOfWeek-1)*6) + "),.moz-text-html div:nth-child(" + (5+(dayOfWeek-1)*6) + ")"));
restaurants[restaurants.length] = (new Restaurant("opice", "U Tří Opic", "Opice #menu>p:not(.dots,#menuTyden)"));
restaurants[restaurants.length] = (new Restaurant("kotelna", "U Kotelny", "Kotelna .tmi-group"));

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
		for (var j = 0; j < restaurants.length; j++) {
			if (localStorage["cz.redhat.oskutka.rhlp.order." + i + ".id"] == restaurants[j].id) {
				isStored = true;
			};
		};
		if (!isStored) {
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
