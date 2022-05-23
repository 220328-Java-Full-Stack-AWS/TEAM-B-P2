import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './pages/landing/landing.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { ProductViewsComponent } from './pages/product-views/product-views.component';
import { UserAddressComponent } from './pages/user-address/user-address.component';
import { UserLoginComponent } from './pages/user-login/user-login.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { UserViewComponent } from './pages/user-view/user-view.component';
import { CartComponent } from './pages/cart/cart.component';

const routes: Routes = [
  {
    path: "",
    pathMatch: 'full',
    component: LandingComponent
  },
  {
    path: 'product-views',
    component: ProductViewsComponent
  },
  {
    path: 'user-view',
    component: UserViewComponent
  },
  {
    path: 'user-login',
    component: UserLoginComponent
  },
  {
    path: 'user-registration',
    component: UserRegistrationComponent
  },
  {
<<<<<<< HEAD
    path: 'user-address',
    component: UserAddressComponent
=======
    path: 'cart',
    component: CartComponent
  }
  ,
  {
    path: 'product-details/:id',
    component: ProductDetailsComponent
>>>>>>> 5c22040f43b7c4c83befe034782d7dbb95dea39d
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
