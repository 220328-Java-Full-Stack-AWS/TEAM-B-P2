import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingComponent } from './pages/landing/landing.component';
import { ProductDetailsComponent } from './pages/product-details/product-details.component';
import { ProductViewsComponent } from './pages/product-views/product-views.component';
import { UserAddressComponent } from './pages/user-address/user-address.component';
import { UserLoginComponent } from './pages/user-login/user-login.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { UserUpdateComponent } from './pages/user-update/user-update.component';
import { UserViewComponent } from './pages/user-view/user-view.component';
import { CartComponent } from './pages/cart/cart.component';
import { CheckoutComponent } from './pages/checkout/checkout.component';
import { OrdersComponent } from './pages/orders/orders.component';
import { UpdateAddressComponent } from './pages/update-address/update-address.component';

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
    path: 'user-update',
    component: UserUpdateComponent
  },
  {
    path: 'user-address',
    component: UserAddressComponent
  },
  {
    path: 'cart',
    component: CartComponent
  }
  ,
  {
    path: 'product-details/:id',
    component: ProductDetailsComponent
  },
  {
    path: 'checkout',
    component: CheckoutComponent
  },
  {
    path: 'orders',
    component: OrdersComponent
  },
  {
    path: 'update-address',
    component: UpdateAddressComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
