import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ApartmentComponent } from './pages/apartment/apartment.component';
import { EditModelsComponent } from './pages/edit-models/edit-models.component';
import { ModelComponent } from './pages/model/model.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'apartments/:id', component: ApartmentComponent },
    { path: 'items-dashboard', component: EditModelsComponent },
    { path: 'items-dashboard/model/:id', component: ModelComponent }
];
