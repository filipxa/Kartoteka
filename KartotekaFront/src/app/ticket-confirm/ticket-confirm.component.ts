import { Component, OnInit, Input } from '@angular/core';
import { Karta } from '../models/karta';
import { TicketService } from '../services/ticket.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-ticket-confirm',
  templateUrl: './ticket-confirm.component.html',
  styleUrls: ['./ticket-confirm.component.css']
})
export class TicketConfirmComponent implements OnInit {

  constructor(private ticketService : TicketService, 
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar) { }
  @Input() karta: Karta;

  ngOnInit() {
    this.getKarta();
  }
  getKarta(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.ticketService.getTicket(id).subscribe(karta => {
      this.karta = karta;
      if(karta.tip=="slobodno"){
        this.router.navigate(["/"]);
      }
    });
  }
  confirmClick(): void{
    this.snackBar.open("Ticket confirmed", "", {
      duration: 2000,
    });
    this.router.navigate(["/"]);
  }

  cancelClick(): void{
    this.ticketService.cancelTicket(this.karta);
    this.router.navigate(["/"]);

  }

  format2digit(text : string):string{
    if(text.length==1){
      text="0" + text;
    }
    return text;
  }

  isCancable(ticket : Karta) : boolean {
    return Karta.isCancable(ticket);
  }


  kartaDisplayDate(ticket : Karta): string{

    let month = this.format2digit("" +Karta.getTermin(ticket).getMonth());
    let day = this.format2digit("" + Karta.getTermin(ticket).getDate());
   
    return month +"."+ day;
  }
  kartaDisplayTime(ticket : Karta): string{
    let hour = this.format2digit( ""+ Karta.getTermin(ticket).getHours());
    let minute = this.format2digit( ""+ Karta.getTermin(ticket).getMinutes());
    return  hour + ":" + minute ;
  }

  kartaLokalName(ticket : Karta) : string  {
    return ticket.lokalNaziv;
  }

}
