import { Component, OnInit } from '@angular/core';
import { TicketService } from '../services/ticket.service';
import { Karta } from '../models/karta';
import { Router } from '@angular/router'
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-tickets-page',
  templateUrl: './tickets-page.component.html',
  styleUrls: ['./tickets-page.component.css']
})
export class TicketsPageComponent implements OnInit {
  tickets : Karta[];
  cancelTickets : Karta[];
  constructor(private ticketService : TicketService,  private router: Router,private userService : UserService ) { }


  ngOnInit() {
    if (this.userService.loggedUser == null) {;
      this.router.navigate(["/"]);
    } else {
      this.getTickets();
    }
    
  }

  format2digit(text : string):string{
    if(text.length==1){
      text="0" + text;
    }
    return text;
  }

   kartaDisplayDate(ticket : Karta): string{

    let month = this.format2digit("" +Karta.getTermin(ticket).getMonth());
    let day = this.format2digit("" + Karta.getTermin(ticket).getDate());
   
    return month +"."+ day;
  }

  isCancable(ticket : Karta) : boolean {
    return Karta.isCancable(ticket);
  }

   kartaDisplayTime(ticket : Karta): string{
    let hour = this.format2digit( ""+ Karta.getTermin(ticket).getHours());
    let minute = this.format2digit( ""+ Karta.getTermin(ticket).getMinutes());
    return  hour + ":" + minute ;
  }

  kartaLokalName(ticket : Karta) : string  {
    return Karta.getLokalName(ticket);
  }

  getTickets() : void{
    this.ticketService.getLoggedUserTickets().subscribe(tickets => {
      this.tickets=tickets;
     
    } );
  }

  cancelTicket(ticket : Karta){
    this.tickets = this.tickets.filter(t => t !== ticket);
    this.ticketService.cancelTicket(ticket);

  }

}
