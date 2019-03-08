import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUsr } from 'app/shared/model/usr.model';

@Component({
    selector: 'jhi-usr-detail',
    templateUrl: './usr-detail.component.html'
})
export class UsrDetailComponent implements OnInit {
    usr: IUsr;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ usr }) => {
            this.usr = usr;
        });
    }

    previousState() {
        window.history.back();
    }
}
