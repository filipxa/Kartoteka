import { Component, OnInit } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { Lokal } from '../models/lokal';
import { LokalService } from '../services/lokal.service';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';


@Component({
  selector: 'app-lokal-search',
  templateUrl: './lokal-search.component.html',
  styleUrls: ['./lokal-search.component.css']
})
export class LokalSearchComponent implements OnInit {
  lokali$: Observable<Lokal[]>;
  private searchTerms = new Subject<string>();

  constructor(private lokalService : LokalService) { }

  search(term : string) : void{
    this.searchTerms.next(term);
  }

  ngOnInit() {
    this.lokali$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.lokalService.searchLokali(term)),
    );
  }

}
