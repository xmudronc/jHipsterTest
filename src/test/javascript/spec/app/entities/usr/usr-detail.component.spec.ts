/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JHipsterTestTestModule } from '../../../test.module';
import { UsrDetailComponent } from 'app/entities/usr/usr-detail.component';
import { Usr } from 'app/shared/model/usr.model';

describe('Component Tests', () => {
    describe('Usr Management Detail Component', () => {
        let comp: UsrDetailComponent;
        let fixture: ComponentFixture<UsrDetailComponent>;
        const route = ({ data: of({ usr: new Usr(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipsterTestTestModule],
                declarations: [UsrDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UsrDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UsrDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.usr).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
