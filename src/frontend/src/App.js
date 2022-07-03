import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.scss';
import {TeamPage} from './pages/TeamPage';
import {MatchPage} from './pages/MatchPage';
import {HomePage} from './pages/HomePage';
function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <Routes>
            <Route path="/" exact element={<HomePage/>}></Route>
            <Route path='/team/:teamName/matches/:year'exact element={<MatchPage/>}></Route>
            <Route path='/team/:teamName' exact element={<TeamPage/>}></Route>
          </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
