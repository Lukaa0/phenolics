import React from "react";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

import SourceListPage from "./pages/SourceListPage";
import PhenolicListPage from "./pages/PhenolicListPage";
import MoleculeListPage from "./pages/MoleculeListPage";
import SourceDetailPage from "./pages/SourceDetailPage";
import PhenolicDetailPage from "./pages/PhenolicDetailPage";
import MoleculeDetailPage from "./pages/MoleculeDetailPage";
import MainNavigation from "./components/MainNavigation";

import { NAV_LINKS } from "./constants/navLinks";

function App() {
  return (
    <BrowserRouter>
      <MainNavigation>
        <Switch>
          <Redirect path="/" to={`/${NAV_LINKS.SOURCE_LIST}`} exact />
          <Route
            path={`/${NAV_LINKS.SOURCE_LIST}`}
            component={SourceListPage}
          />
          <Route
            path={`/${NAV_LINKS.PHENOLIC_LIST}`}
            component={PhenolicListPage}
          />
          <Route
            path={`/${NAV_LINKS.MOLECULE_LIST}`}
            component={MoleculeListPage}
          />
          <Route
            path={`/${NAV_LINKS.SOURCE_DETAIL}`}
            component={SourceDetailPage}
          />
          <Route
            path={`/${NAV_LINKS.PHENOLIC_DETAIL}`}
            component={PhenolicDetailPage}
          />
          <Route
            path={`/${NAV_LINKS.MOLECULE_DETAIL}`}
            component={MoleculeDetailPage}
          />
        </Switch>
      </MainNavigation>
    </BrowserRouter>
  );
}

export default App;
