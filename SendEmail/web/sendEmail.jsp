<%-- 
    Document   : sendEmail
    Created on : Mar 14, 2015, 5:55:37 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"></script>
        <script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
    <title>Sending Email</title>
</head>
<body>
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <a class="navbar-brand" href="#">Send Email</a>
            <p class="navbar-text navbar-right">
                Prepared By <a href="#" class="navbar-link">
                    Ajit Kumar Baral</a></p>
        </div>
    </nav>

    <div class="col-xs-4">
        <form id="sendEmailForm" method="POST">
            <div class="form-group">
                <label>Smtp</label>
                <input type="text" placeholder = "smtp.example.com" 
                       name="smtp" size="100" id="smtp" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Your Email Address </label>
                <input type="text" placeholder = "john.doe@example.com" 
                       name="sender" size="100" id="sender" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Receiver's Email Address</label>
                <input type="text" placeholder = "roe.joe@example.com" 
                       name="receiver" size="100" id="receiver" class="form-control"/>
            </div>
            <div class="form-group">
                <label>Subject</label>
                <input type="textarea" placeholder = "Subject:" 
                       name="subject" size="100" id="subject" class="form-control"/>
            </div>
            <div class="form-group">
                <label for="comment">Message</label>
                <textarea class="form-control" id="message" name="message" rows="5"></textarea>
            </div>
        </form>
        
            <div class="form-group">
                <button class="btn btn-info pull-right" id="btnSendEmail">Send Email</button><br/><br/>
            </div>
    </div>

    <div class="form-group col-xs-12" id="resultDiv">
        <label>Result</label>
        <input disabled="true" id="result" class="form-control"/>

    </div>

    <script>
        $(document).on('ready', function () {
            $("#resultDiv").hide();

            $("#btnSendEmail").on('click', function () {
               var smtp = $("#smtp").val();
               var sender = $("#sender").val();
               var receiver = $("#receiver").val();
               var subject = $("#subject").val();
               var message = $("#message").val();
                
                if(smtp !== "" && sender !=="" && receiver!=="" 
                        && subject!=="" && message !=="" ){
                    
                $.post("http://localhost:8080/SendEmail/api/sendEmail", $("#sendEmailForm").serialize(), 
                function (data) {
                    $("#resultDiv").show();
                    $("#result").val(data);
                });
                }else{
                    $("#resultDiv").show();
                    $("#result").val("Please fill all the fields");
                }
            });
        });
    </script>

</body>
</html>
