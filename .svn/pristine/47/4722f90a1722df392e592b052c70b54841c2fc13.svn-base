$(function() {
	$("#inscription").hide();
	$('#menuProfesseur').hide();
	$("#envoiInscr")
			.on(
					"click",
					function() {
						// On vérifie si les deux mots de passes entrés sont
						// identiques

						if ($("#mdp").val() != $("#mdp2").val()) {
							$(".notification")
									.html(
											"<p>Les deux mots de passes ne sont pas identiques</p>");

							// S'il le sont on envoi le formulaire a la servlet
						} else {
							var json = formToJson($("#formInscr"));
							$
									.ajax({
										url : "/app",
										type : "POST",
										data : "requete=inscription&form="
												+ JSON.stringify(json),
										success : function() {
											window.location
													.replace("Webcontent/page.html");
										},
										error : function(jqXHR, textStatus,
												errorThrown) {
											$(".notification").html(
													"<p>L'inscription à ratée: "
															+ errorThrown
															+ "<p>");
										}
									});
						}
						;
						return false;
					});

	$("#envoiAuth").on(
			"click",
			function() {
				$.ajax({
					url : "/app",
					type : "POST",
					data : "requete=authentifie&pseudo=" + $("#pseudo").val()
							+ "&mdp=" + $("#password").val(),
					success : function() {
						window.location
								.replace("Webcontent/page.html");
					},
					error : function(jqXHR, textStatus, errorThrown) {
						$(".notification").html(
								"<p>La connection à ratée: " + errorThrown
										+ "<p>");
					}
				});
				return false;
			});

	$("#inscr").on("click", function() {
		$("#connexion").hide();
		$("#inscription").show();
		return false;
	});

	function formToJson(form) {
		var data = {};

		form.find('input').each(function(i, value) {
			data[$(value).attr('name')] = $(value).val();
		});

		form.find('select').each(function(i, value) {
			data[$(value).attr('name')] = $(value).val();
		});

		return data;
	}
	;
});
