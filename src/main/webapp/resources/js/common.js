//Message duration
const messageTime = setTimeout(message, 2000);
//reset data.
const resetData = { "day": "", "profile": "", "reject": "", "time": "" };
//append multiple child
Element.prototype.addChildren = function(children) {
	for (var x of children) {
		this.appendChild(x);
	}
};
//To prevent multiple click occour on event that can couse abnormal behaviour
Element.prototype.triggerClick = function() {
	$(this).addClass('clicked');
	var x = this;
	setTimeout(function() { $(x).removeClass('clicked') }, 500);
}
//document ready function
$(function() {
	$('.btn-gnavi').on('click', function() {
		var rightVal = 0;
		if ($(this).hasClass('dash')) {
			if ($(this).hasClass('active')) {
				rightVal = -50;
				$(this).removeClass('active');
			} else {
				$(this).addClass('active');
			}
			$('.left-navigator').stop().animate({
				left: rightVal + "%"
			}, 200);
		} else {

			if ($(this).hasClass('hb-open')) {
				rightVal = -100;
				$(this).removeClass('hb-open');
			} else {
				$(this).addClass('hb-open');
			}

			$('#global-navi').stop().animate({
				right: rightVal + "vw"
			}, 200);
		}

	});
	//data table
	$('table').DataTable({
		searching: false,
		paging: false,
		info: false
	});;
	//height line
	$('.das > div').heightLine({
		fontSizeCheck: true
	});
	$('.doc-hl').heightLine({
		fontSizeCheck: true
	});
	//booking status
	$(".select-status").on("change", function() {
		console.log(this.value);
		if (this.value.trim() == "Rejected") {
			$(".reject-msg").removeClass("hide");
			$(".reject-msg").addClass("show");
			$("#rj-msg").attr("required", "required");
			$("#rj-msg").val(resetData.reject);
		} else {
			resetBooking();
		}
	});

	//pre checking selected doctor date and time.
	if ($('#dutytime')[0] != null && $('#selector-inp')[0] != null) {
		if ($('#userId'[0] != null))
			preLoadingDT();
	}
	//pre set rejected message data
	if ($('#rj-msg')[0] != null) {
		resetData.reject = $('#rj-msg').val();
	}
	//pre set profile data
	if ($('#dis-profile')[0] != null) {
		resetData.profile = $('#dis-profile').attr("src");
	}
	//schedule activer
	if ($('.sec-activer')[0] != null) {
		$('.sec-activer').each(function() {
			var sec = $(this).find('.dow').first()[0].innerHTML.trim();
			console.log(sec, getToday());
			if (sec == getToday()) {
				$(this).addClass('active');
			}
		});
	}
	//advance check the focus for selector box
	$('.selectors').focusout(function() {
		$(this).slideUp(350, function() {
			$('.select-ico').removeClass('active');
			$(this).removeClass("active");
		});
	});
	$('.selectors').focusin(function() {
		$(this).slideDown(350, function() {
			this.focus();
			$(this).addClass("active");
		});
	});
	//time selection
	$(".time").on("input", function(e) {
		e.preventDefault();
		applySelectedTime();
	});
	//day selection
	$(".select-ico").on('click', function(e) {
		e.preventDefault();
		$(this).toggleClass('active');
		var check = $(this).hasClass('active');
		check ? $('.selectors').focusin() : $('.selectors').focusout();
	});
	//select days click event
	$(".day-selector .days").on("click", function(e) {
		e.stopPropagation();
		if ($(this).hasClass('clicked')) {
			return;
		}
		if (!$(this).hasClass('selected')) {
			var item = createSelected(this);
			$('.selection').append($(item));
			$(this).addClass('selected');
			$('.selection')[0].addChildren(setDayOrder());
			applySelectedDay();
			$('.item').each(function() {
				if (this.innerHTML == item.innerHTML) {
					$(this).addClass('animate');
				}
			});
		} else {
			$("#_" + this.innerHTML).fadeOut(350, function() {
				$(this).remove();
			});
			$(this).removeClass('selected');
		}
		this.triggerClick();
	});
	//doctor select
	$('.select-doc').on("change", function() {
		$('#doctor-id').attr("value", $(this).find(":selected").val());
	});
	//clear form
	$('.clear-btn').on('click', function() {
		$('.form-inp').val("");
		//check data reset for doctor
		if (this.dataset['reset'] == 1) {
			resetDoctorData();
		}
		if (this.dataset['clear'] == "1") {
			$('.select-doc').children().first()[0].selected = true;
		}
		$('#form input:radio').each(function() {
			this.checked = false;
		});
	});
	//Reset button onclick
	$('.reset-btn').on('click', function() {
		$('#form')[0].reset();
		//check data reset for doctor
		if (this.dataset['reset'] == 1) {
			resetDoctorData();
		}
		//check data reset for booking
		if (this.dataset['reset'] == 2) {
			resetBooking();
		}
		//check for clear data set that trigger on reset
		if (this.dataset['clear'] == 1) {
			$('#doctor-id').attr("value",$('.select-doc').children().first().val());
		}
	});
	//choose photo
	$('.choose').on('click', function(e) {
		e.preventDefault();
		$('#choose').click();
	});
	//handle choose photo
	$('#choose').on('change', choosePhoto);
	//footer tab
	$('.tab').on('click', function() {
		var width = $(window).width();
		if (width < 768) {
			var parent = this.parentElement;
			$('.index').each(function() {
				if (this != parent) {
					$(this).removeClass('active');
				} else {
					$(this).addClass('active');
				}
			});
		}
	});
});
//duration function
function message() {
	$('.message-field').slideUp(350, function() {
		$(this).remove();
	});
}
//pre loading doctor duty day and time
function preLoadingDT() {
	var time = $('#dutytime')[0];
	var times = stringTokenizer(time.value, '-');
	var day = $('#selector-inp')[0];
	var days = stringTokenizer(day.value, ',');
	resetData.time = time.value;
	resetData.day = day.value;
	if (times.length) {
		$('.time').each(function() {
			if ($(this).data('time') == 'start') {
				this.value = convert12To24HrFormat(times[0].trim());
			}
			if ($(this).data('time') == 'end') {
				this.value = convert12To24HrFormat(times[1].trim());
			}
		});
	}
	if (days.length) {
		for (var day of days) {
			var x = document.createElement('span');
			x.innerHTML = day.trim();
			var item = createSelected(x);
			$('.selection').append($(item));
			$('.days').each(function() {
				if ($(this).data('day') == day.trim()) {
					$(this).addClass("selected");
				}
			});
		}
		$('.selection')[0].addChildren(setDayOrder());
		applySelectedDay();
	}
}
//String Tokenizer
function stringTokenizer(str, pattern) {
	var strs = [];
	if (str.length > 0) {
		strs = str.split(pattern);
	}
	return strs;
}
//create and append day selector
function createSelected(selector) {
	var item = document.createElement('div');
	$(item).addClass("days item selected lf clearfix");
	item.id = "_" + selector.innerHTML.trim();
	item.title = "Remove from selection";
	var daywrp = document.createElement('span');
	$(daywrp).addClass('days-wrp lf');
	daywrp.innerHTML = selector.innerHTML;
	var cls = document.createElement('span');
	$(cls).addClass("unselect-day fa-usage close-ico rg");
	$(item).on('click', function(e) {
		e.stopPropagation();
		var day = $(this).children().first().text();
		$(".day-selector").children().each(function() {
			var cmd = $(this).data('day');
			if (cmd == day) {
				$(this).removeClass('selected');
			}
		});
		$(this).fadeOut(350, function() {
			$(this).remove();
			applySelectedDay();
		});
	});
	item.append(daywrp);
	item.append(cls);
	return item;
}
//apply selected time
function applySelectedTime() {
	var time = $('#dutytime')[0];
	var selected = "";
	var start = "";
	var end = "";

	$('.time').each(function() {
		var cmd = $(this).data('time');

		if (cmd == "start") {
			start = $(this).val();
		}
		if (cmd == "end") {
			end = $(this).val();
		}
	});
	if (start != "" && end != "") {
		var submitor;
		$('.btn.sub').each(function() {
			if (this.value == "Confirm") {
				submitor = this;
				return false;
			}
		});
		var validate = dutyTimeValidator(start, end);
		if (validate.isValid) {
			$('#dutytime').parent().find('.err-txt').first().text("");
			selected = convert24To12HrFormat(start) + " - " + convert24To12HrFormat(end);
			$(submitor).removeClass("disabled");
			$(submitor).removeAttr("disabled");
		} else {
			$('#dutytime').parent().find('.err-txt').first().text(validate.msg);
			$(submitor).addClass("disabled");
			$(submitor).attr("disabled", "disabled");
		}
	}
	$(time).attr("value", selected);
}
//get greatest hour
function dutyTimeValidator(start, end) {
	var time1 = stringTokenizer(start, ':');
	var hr1 = parseInt(time1[0]);
	var min1 = parseInt(time1[1]);

	var time2 = stringTokenizer(end, ':');
	var hr2 = parseInt(time2[0]);
	var min2 = parseInt(time2[1]);

	var check = true;
	var msg = "";
	if (hr1 > hr2 || hr1 == hr2 && min1 > min2) {
		check = false;
		msg = "End Time must greater than Start Time!";
	} else if (hr1 == hr2 && min1 == min2) {
		check = false;
		msg = "Start Time and End Time cannot be same";
	}
	return {
		isValid: check,
		msg: msg
	}
}
//apply selected day
function applySelectedDay() {
	var selectorinput = $('#selector-inp')[0];
	var selected = "";
	$('.days.item').each(function() {
		selected += $(this).text() + ", ";
	});
	selected = selected.trim();
	selected = selected.slice(0, selected.length - 1);
	$(selectorinput).attr("value", selected);
}
//order days
function setDayOrder() {
	const days = {
		"sunday": 0,
		"monday": 1,
		"tuesday": 2,
		"wendesday": 3,
		"thursday": 4,
		"friday": 5,
		"saturday": 6,
	};
	let selected = [];
	$('.days.item').each(function() {
		selected.push({ "day": $(this).text().toLowerCase().trim() });
		$(this).remove();
	});
	selected.sort(function(a, b) {
		let first = a.day;
		let second = b.day;
		return days[first] > days[second] ? 1 : days[first] < days[second] ? -1 : 0;
	});
	var sorted = [];
	var child;
	for (var x of selected) {
		child = document.createElement('span');
		child.innerHTML = x.day.charAt(0).toUpperCase() + x.day.slice(1);
		sorted.push(createSelected(child));
	}
	return sorted;
}
//reset doctor data
function resetDoctorData() {
	$('#dutytime').attr("value", resetData.time);
	$('#selector-inp').attr("value", resetData.day);
	$('#dis-profile').attr("src", resetData.profile);
	$('.selection').children().each(function() {
		this.remove();
	});
	$('.days').each(function() {
		$(this).removeClass('selected');
	});
	preLoadingDT();
}
//reset booking 
function resetBooking() {
	if ($('.select-status').val() != 'Rejected') {
		$('.reject-msg').removeClass('show');
		$('.reject-msg').addClass('hide');
		$('#rj-msg').val("");
		$('#rj-msg').removeAttr('required');
	} else {
		$(".reject-msg").removeClass("hide");
		$(".reject-msg").addClass("show");
		$("#rj-msg").attr("required", "required");
		$("#rj-msg").val(resetData.reject);
	}
}
//choose photo
function choosePhoto(evt) {
	var files = evt.originalEvent.target.files;
	var img = document.getElementById('dis-profile');
	if (files.length === 0) {
		console.log('No file is selected !');
		return;
	}
	var reader = new FileReader();
	reader.onload = function(event) {
		img.src = event.target.result;
	};
	reader.readAsDataURL(files[0]);
}
//change time format
function convert24To12HrFormat(time) {
	var hr, min, meri;
	var times = stringTokenizer(time, ':');
	hr = times[0];
	min = times[1];
	if (hr > 12) {
		meri = "PM";
		hr -= 12;
	} else if (hr < 12) {
		meri = "AM";
		if (hr == 0)
			hr = 12;
	} else {
		meri = "PM";
	}
	return hr + ":" + min + " " + meri;
}
function convert12To24HrFormat(time) {
	const [times, meri] = time.split(' ');
	let [hours, minutes] = times.split(':');
	if (hours === '12') {
		hours = '00';
	}
	if (meri === 'PM') {
		hours = parseInt(hours, 10) + 12;
	}
	return hours + ':' + minutes;
}

function getToday() {
	var day = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
	var date = new Date();
	return day[date.getDay()];
}