
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
	["opice", "U 3 Opic", "Opice"],
	["molino", "Molino", "Molino #category-content table"],
	["sporthotel", "A-Sport Hotel", "Sporthotel"],
	["kotelna", "U Kotelny", "Kotelna"],
	["prometheus", "Prometheus", "Prometheus .daily-menu"],
	["vista", "Hotel Vista", "Vista"],
	["menza", "Menza Purkyňova", "Menza #m10"],
	["velorex", "Velorex", "Velorex #denniNabidka > :nth-child(n+2) "],
	//["myfood", "My Food Truck", "Myfood #dailyMenu"],
	["rebio", "Rebio", "Rebio"],
	["vaclav", "Vaclav", "Vaclav .content table:first-of-type"],
	["welcome", "Welcome", "Welcome .et_pb_text_" + dayOfWeek + " tr:nth-child(n+2)"],
	["bavorska", "Bavorská", "Bavorska .menicka:first-of-type :nth-child(n+3)"],
	["nepal", "Nepal", "Nepal tr:nth-child(n+2)"],
	["padthai", "Pad Thai", "PadThai"],
	["crocus", "Crocus jidelna (Winston)", "Crocus .menicka:nth-of-type(2) :nth-child(n+3)"],
	["cookpoint", "Cook Point", "Cookpoint .hpbox.first table"],
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
