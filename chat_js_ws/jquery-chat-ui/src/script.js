$(document).ready(function(){
  
  $('.message-icon').click(function(){
    $('.chat').fadeToggle(500);
  });
  
  // for nicescroll
  // $('.messages-box').niceScroll({
  //   cursorcolor:'#5290F5',
  //   cursoropacitymin:0.7,
  //   cursorborder:'none',
  //   cursorborderradius: "3px",
  //   scrollspeed: 400,
  //   background: "#ddd",
  //   railoffset: {left: 7},
  //   cursorwidth :'4px'
  // });
  
  // form submit
  $('form').submit(function(e){
    e.preventDefault();
    var $messagesBox = $( ".messages-box" ),
        messagesBoxHeight = $messagesBox[0].scrollHeight,
        message = $('input', this).val(),
        messageLength = message.length;
        
        if(messageLength > 0){
          $('input', this).removeClass('error');
          $messagesBox.append('<div class="message"><i class="fa fa-close"></i> <p>' + message +'</p></div>');
        }else{
          $('input', this).addClass('error');
        }
        
        $('input',this).val('');
        $('input',this).focus();
    
     // scroll to see last message
     $messagesBox.scrollTop( messagesBoxHeight );
    
  });  // form
  
  // delete massage
  $(document).on('click', '.fa-close', function(){ 
     $(this).parent().fadeOut(500,function(){
      $(this).remove();
      });
   }); 
  
  // mouse enter add class
  $(document).on('mouseenter', '.fa-close', function(){
    $(this).parent().addClass('active');
  });
  
  // mouse leave remove class
  $(document).on('mouseleave', '.fa-close', function(){
    $(this).parent().removeClass('active');
  });
  
});  // document ready

  