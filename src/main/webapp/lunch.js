
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

var restaurants = [
	["kanas_restaurace", "Kanas - Restaurace", "Kanas #tab1 *:nth-child(n+2)"],
	["kanas_jidelna", "Kanas - Jídelna", "Kanas #tab2 *:nth-child(n+2)"],
	//["paladeo", "Paladeo", "Paladeo"],
	["purkynka", "Purkyňka", "Purkynka"],
	["mozzarella", "Pizzerie Mozzarella", "Mozzarella #m18"],
	["opice", "U 3 Opic", "Opice"],
	["sporthotel", "A-Sport Hotel", "Sporthotel"],
	["kotelna", "U Kotelny", "Kotelna"],
	["zelena_kocka", "Zelena Kocka", "ZelenaKocka #dnesni-menu"],
	["globus", "Globus", "Globus .restaurant__menu-table-row--active .restaurant__menu-food-table "],
	["menza", "Menza Purkyňova", "Menza #m10"],
	["menzaFch", "Studentská jídelna FCH", "MenzaFch #m16"],
	["menzaKolejni", "Menza Kolejní", "MenzaKolejni #m20"],
	["velorex", "Velorex", "Velorex #denniNabidka > :nth-child(n+2) "],
	//["myfood", "My Food Truck", "Myfood #dailyMenu"],
	["nepal", "Nepal", "Nepal"],
	["tasteOfIndia", "Taste of India", "TasteOfIndia"],
	["padthai", "Pad Thai", "PadThai"],
	["crocus", "Crocus jidelna (Winston)", "Crocus .menicka:nth-of-type(2) > :nth-child(n+2)"],
	["cookpoint", "Cook Point", "Cookpoint .hpbox table"],
	["brnenka", "Brněnka", "Brnenka"],
	["portoriko", "Portoriko", "Portoriko"],
	["charliesmill", "Charlie's Mill", "CharliesMill"],
	["spravnemisto", "Správné Místo", "SpravneMisto"],
	["liquidbread", "Liquid Bread", "LiquidBread"],
	["jpbistro", "Jean Paul's Bistro", "JPBistro"],
].map(function(data) {
	return new Restaurant(data[0], data[1], data[2]);
});

function isTouchDevice() {
	return ('ontouchstart' in document.documentElement);
}

function getRestaurant(id) {
	return restaurants.filter(function(restaurant) { return restaurant.id == id; })[0] || null;
	// TODO handle error
}


function isLocalStorageOk() {
	storageOk = true;
	for (var i = 0; i < restaurants.length && storageOk; i++) {
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
