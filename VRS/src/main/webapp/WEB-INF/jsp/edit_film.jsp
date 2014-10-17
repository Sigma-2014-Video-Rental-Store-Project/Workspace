<?xml version="1.0" encoding="ISO-8859-1" ?>
<jsp:directive.page language="java"
                    contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"/>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:c="http://java.sun.com/jsp/jstl/core">
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/edit_film.css" rel="stylesheet">
<link href="css/bootstrap-spinedit.css" rel="stylesheet" >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Film edit page.">
    <meta name="author" content="Vlad Samotskiy">
    <link rel="icon" href="">
    <title>Films edit</title>
</head>
<body>
    <f:view>
        <div id="header">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>
        <form action="controller" method="post" name="editFilm">
            <input type="hidden" name="command" value="editFilm" />
            <input type="hidden" name="filmId" value="93" />
		    <div id="content-body">
			    <div id=leftside>
				    <p id="text">Film name:</p>
				    <input type="text" name="filmTitle" class="form-control" style="margin-bottom:2%;">
				    <div id="bordered" style="margin-bottom:2%;">
					    <img id="cover" data-src="holder.js/140x140" class="center" src="http://cs540107.vk.me/c7005/v7005726/2c818/I-bf-lL08Vg.jpg" >
				    </div>
				    <input type="file" id="inputFile" onChange="setUpCoverRepresentation(this);">
			    </div>
			    <div id=rightside>
				    <div>
					    <div id="genre" style="margin-bottom:1.5%;">
						    <p id="text">Genre:</p>
						    <select name="categoryName" class="form-control">
							    <option>Comedy</option>
						    </select>
					    </div>
					    <div style="float:right;">
						    <button type="button" class="btn btn-danger" onclick="setSeveralAttr(['filmId', 'remove'],[filmId.value, 'true'])">Remove film</button>
					    </div>
				    </div>
				    <div>
					    <textarea name="description" class="form-control" rows="6" style="margin-bottom:2%;margin-top:10%;"></textarea>
				    </div>
				    <div>
					    <div style="float:left; width:45%;">
						    <div style="float:left;">
							    <p id="text">Copies: </p>
						    </div>
						    <div style="float:right; width:30%">
							    <input type="text" name="amount" class="form-control" style="margin-bottom:2%; margin-left:3%;">
						    </div>
						</div>
						<div style="float:right; width:45%; max-width:45%;">
								<div style="float:left;">
									<p id="text">General price: </p>
								</div>
								<div style="float:right; width:30%;">
									<input type="text" name="generalPrice"  class="form-control" style="margin-bottom:2%; margin-left:3%;">
								</div>
						</div>
					</div>
					<div>
					    <div style="float:left; width:45%;">
						    <div style="float:left;">
							    <p id="text">Rent price: </p>
						    </div>
						    <div style="float:right; width:30%">
							    <input name="rentPrice" type="text" class="form-control" style="margin-bottom:2%; margin-left:3%;">
						    </div>
						</div>
						<div style="float:right; width:45%; max-width:45%;">
							
								<div style="float:left;">
									<p id="text" >Bonus value: </p>
								</div>
								<div style="float:right; width:30%;">
									<input name="bonus" type="text" class="form-control" style="margin-bottom:2%; margin-left:3%;">
								</div>
						</div>
					</div>
					<div>
					    <div style="float:left; width:45%;">
						    <div style="float:left;">
							    <p id="text">Year: </p>
						    </div>
						    <div style="float:right; width:30%">
							    <input name="year" type="text" class="form-control" style="margin-bottom:2%; margin-left:3%;">
						    </div>
						</div>
					</div>
					<div>
						<div style="float:right; clear:left; width:50%;">
							<div style="float:right;">
								<button type="submit" class="btn btn-success" style="margin-left:3%;">Save</button>
							</div>
							<div style="float:right;">
								<button type="submit" class="btn btn-default">Cancel</button>
							</div>
						</div>
					</div>
				</div>
				<div id="footer">
					<jsp:include page="../jspf/footer.jspf"/>
				</div>
            </div>
       </form>
    </f:view>
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap-spinedit.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/attribute_funcs.js"></script>
<script type="text/javascript" src="js/cover.js"></script>
</html>