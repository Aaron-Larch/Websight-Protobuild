/*!
 * JavaScript Methods (https://github.com/Aaron-Larch/Websight-Protobuild)
 * Copyright 2020-2025 GCE, Inc.
 * Created by Aaron Larch
 */

$(document).ready(function () {
	// Get a modal
	this.loadModal = function(input){
		$("#AssineParamiters").modal().on('shown.bs.modal', function (e) {loadValues(".PopUpModal", ".CreateNewRecord", input)});
		load_page('/WEB-INF/ModelLibrary.jsp #container');
	};
	
	this.getModal = function(){
		$("#CreateNewRecord").modal().on('shown.bs.modal');
		load_page('/WEB-INF/ModelLibrary.jsp #container');
	};
	
	//function to call popup window
	this.loadPopUp =function (input){
		$("#PopUpModal").modal().on('shown.bs.modal', function (e) {loadContent(input);});
		load_page('/WEB-INF/Objectcontent.jsp #container');
	};
	
	//Switch states button
	this.SwichLoadout= function(a){
		if(a==1){
			[].forEach.call(document.querySelectorAll(".Populate"), function (i) {
			  	i.style.display="block";});
			[].forEach.call(document.querySelectorAll(".display"), function (i) {
			  	i.style.display="none";});
			}else{
				[].forEach.call(document.querySelectorAll(".Populate"), function (i) {
			  		i.style.display="none";});
				[].forEach.call(document.querySelectorAll('.display'), function (i) {
			  		i.style.display="block";});
			}
		};

	//Check all function
	this.checkAll=function(source) {
		  checkboxes = document.getElementsByName('operation');
		  for(var i=0; i<checkboxes.length; i++) {
		    checkboxes[i].checked = source.checked;
		  }
		};
})	

function load_page(url){$('modal-isi-body').load(url) ;}

//Display a combination of classes based of a given variabule
function loadValues(class1, class2, flag){
	if(flag=="page1"){
		[].forEach.call(document.querySelectorAll(class1), function (i) {i.style.display="block";});
		[].forEach.call(document.querySelectorAll(class2), function (i) {i.style.display="none";});
	}else if(flag=="page2"){
		[].forEach.call(document.querySelectorAll(class2), function (i) {i.style.display="block";});
		[].forEach.call(document.querySelectorAll(class1), function (i) {i.style.display="none";});
		}
}

//Select a string from an array of strings based on user input 
function loadContent(input) {
	document.getElementById("Display").value = "";
	//convert string into an array of strings
	var JsArray = document.getElementById('DisplayValue').innerHTML;
	var block = JsArray.substring(1, JsArray.length-1);
	var string = block.split("break");
	if(input > 0){ //Search array
		var quickfix=string[input].substring(2, string[input].length);
		document.getElementById('Display').value=quickfix;
	}else{document.getElementById('Display').value=string[input];}
}