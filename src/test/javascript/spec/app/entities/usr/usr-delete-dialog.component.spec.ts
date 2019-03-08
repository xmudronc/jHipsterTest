/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JHipsterTestTestModule } from '../../../test.module';
import { UsrDeleteDialogComponent } from 'app/entities/usr/usr-delete-dialog.component';
import { UsrService } from 'app/entities/usr/usr.service';

describe('Component Tests', () => {
    describe('Usr Management Delete Component', () => {
        let comp: UsrDeleteDialogComponent;
        let fixture: ComponentFixture<UsrDeleteDialogComponent>;
        let service: UsrService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JHipsterTestTestModule],
                declarations: [UsrDeleteDialogComponent]
            })
                .overrideTemplate(UsrDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UsrDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UsrService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
